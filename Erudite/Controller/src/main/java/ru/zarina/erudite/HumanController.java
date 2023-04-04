package ru.zarina.erudite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.services.HumanService;

@RestController
@RequestMapping("/humans")
public class HumanController {
    private final HumanService humanService;

    @Autowired
    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping
    public ResponseEntity<HumanDto> findHuman(@RequestParam("name") String name) {
        return ResponseEntity.ok(humanService.findHuman(name));
    }
}
