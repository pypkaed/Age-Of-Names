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
import ru.zarina.erudite.responseEntities.FileUploadResponse;
import ru.zarina.erudite.services.FileService;
import ru.zarina.erudite.services.HumanService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/humans")
public class HumanController {
    private final HumanService humanService;
    private final FileService fileService;
    private final ControllerExceptionHandler exceptionHandler;

    @Autowired
    public HumanController(HumanService humanService, FileService fileService) {
        this.humanService = humanService;
        this.fileService = fileService;
        exceptionHandler = new ControllerExceptionHandler();
    }

    @GetMapping()
    public ResponseEntity<HumanDto> findHuman(@RequestParam("name") String name) {
        HumanDto human = null;

        try {
            human = humanService.findHuman(name);
        }
        catch (HumanServiceException e) {
            try {
                human = humanService.addHuman(name, null);
            }
            catch (HumanServiceException ex) {
                exceptionHandler.handleHumanServiceException(ex);
            }
        }

        return ResponseEntity.ok(human);
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
                    humanDtos.add(fileService.parseLine(line));
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
