package ru.zarina.erudite.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.List;

@Component
public class NameLengthGreaterThanFilter implements ComparisonFilter {
    private final HumanRepository humanRepository;
    private Integer length;
    @Autowired
    public NameLengthGreaterThanFilter(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public void setValue(Integer value) {
        this.length = value;
    }

    @Override
    public String getName() {
        return "nameLengthGreaterThan_";
    }

    @Override
    public List<HumanDto> apply() {
        return EntitiesMapping.asDto(humanRepository.findByNameLengthGreaterThanEqual(length));
    }
}
