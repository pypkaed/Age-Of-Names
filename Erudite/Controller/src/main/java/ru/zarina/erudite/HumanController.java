package ru.zarina.erudite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.services.FileService;
import ru.zarina.erudite.services.HumanService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/humans")
public class HumanController {
    private final HumanService humanService;
    private final FileService fileService;

    @Autowired
    public HumanController(HumanService humanService, FileService fileService) {
        this.humanService = humanService;
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<HumanDto> findHuman(@RequestParam("name") String name) throws Exception {
        HumanDto human;

        try {
            human = humanService.findHuman(name);
        }
        catch (Exception e) {
            human = humanService.addHuman(name, null);
        }

        return ResponseEntity.ok(human);
    }
    @PostMapping("/upload")
    public ResponseEntity<List<HumanDto>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(fileService.parseFile(file.getInputStream()));
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
