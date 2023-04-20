package ru.zarina.erudite.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.List;

@Component
public class NameLengthFilter implements ComparisonFilter {
    private final HumanRepository humanRepository;
    private Integer length;
    @Autowired
    public NameLengthFilter(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public void setValue(Integer value) {
        this.length = value;
    }

    @Override
    public String getName() {
        return "nameLength_";
    }

    @Override
    public List<HumanDto> apply() {
        return EntitiesMapping.asDto(humanRepository.findByNameLength(length));
    }
}
