package ru.zarina.erudite.responseEntities;

import lombok.Getter;
import lombok.Setter;
import ru.zarina.erudite.dtos.HumanDto;

import java.util.List;

@Getter
@Setter
public class FileUploadResponse {
    private List<HumanDto> humanDtos;
    private List<String> errors;

    public FileUploadResponse(List<HumanDto> humanDtos, List<String> errors) {
        this.humanDtos = humanDtos;
        this.errors = errors;
    }
}