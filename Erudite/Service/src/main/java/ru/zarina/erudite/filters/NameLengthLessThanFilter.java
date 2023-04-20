package ru.zarina.erudite.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.List;

@Component
public class NameLengthLessThanFilter implements ComparisonFilter {
    private final HumanRepository humanRepository;
    private Integer length;
    @Autowired
    public NameLengthLessThanFilter(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public void setValue(Integer value) {
        this.length = value;
    }

    @Override
    public String getName() {
        return "nameLengthLessThan_";
    }

    @Override
    public List<HumanDto> apply() {
        return EntitiesMapping.asDto(humanRepository.findByNameLengthLessThanEqual(length));
    }
}
