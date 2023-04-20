package ru.zarina.erudite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zarina.erudite.exceptions.ControllerExceptionHandler;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.exceptions.FileServiceException;
import ru.zarina.erudite.exceptions.HumanServiceException;
import ru.zarina.erudite.filters.ComparisonFilter;
import ru.zarina.erudite.filters.Filter;
import ru.zarina.erudite.responseEntities.FileUploadResponse;
import ru.zarina.erudite.services.FileService;
import ru.zarina.erudite.services.HumanService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/humans")
public class HumanController {
    private final HumanService humanService;
    private final FileService fileService;
    private final ControllerExceptionHandler exceptionHandler;
    private final Map<String, Integer> requestsPerName;
    private final Map<String, Filter> filters;

    @Autowired
    public HumanController(
            HumanService humanService,
            FileService fileService,
            List<Filter> filters) {
        this.humanService = humanService;
        this.fileService = fileService;
        exceptionHandler = new ControllerExceptionHandler();

        requestsPerName = new HashMap<>();
        this.filters = filters.stream()
                .collect(Collectors.toMap(
                        Filter::getName,
                        Function.identity()
                ));
    }

    @GetMapping("/search")
    public ResponseEntity<HumanDto> findHuman(@RequestParam("name") String name) {
        HumanDto human = null;

        try {
            human = humanService.findHuman(name);
            requestsPerName.put(name.toLowerCase(), requestsPerName.get(name.toLowerCase()) + 1);
        }
        catch (HumanServiceException e) {
            try {
                human = humanService.addHuman(name, null);
                requestsPerName.put(name.toLowerCase(), 1);
            }
            catch (HumanServiceException ex) {
                exceptionHandler.handleHumanServiceException(ex);
            }
        }

        return ResponseEntity.ok(human);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HumanDto>> findAll() {
        return ResponseEntity.ok(humanService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<HumanDto>> find(@RequestParam("filter") String filter,
                                               @RequestParam(value = "value", required = false) Integer value) {
        Filter f = filters.get(filter);

        if (f == null) {
            return ResponseEntity.notFound().build();
        }

        if (f instanceof ComparisonFilter) {
            ((ComparisonFilter) f).setValue(value);
        }

        return ResponseEntity.ok(f.apply());
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Integer>> getRequestsPerName() {
        return ResponseEntity.ok(requestsPerName);
    }

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        List<HumanDto> humanDtos = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try {
            var reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    var humanDto = fileService.parseLine(line);
                    humanDtos.add(humanDto);
                    if (!requestsPerName.containsKey(humanDto.name().toLowerCase())) {
                        requestsPerName.put(humanDto.name().toLowerCase(), 1);
                    }
                }
                catch(IOException | FileServiceException | IllegalArgumentException e) {
                    errors.add(e.getMessage());
                }
            }
        }
        catch (IOException e) {
            exceptionHandler.handleIOException(e);
        }

        var response = new FileUploadResponse(humanDtos, errors);

        if (errors.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }
}
