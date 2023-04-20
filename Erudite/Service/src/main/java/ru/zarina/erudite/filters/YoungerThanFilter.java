package ru.zarina.erudite.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.List;

@Component
public class YoungerThanFilter implements ComparisonFilter {
    private final HumanRepository humanRepository;
    private Integer age;
    @Autowired
    public YoungerThanFilter(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public void setValue(Integer value) {
        this.age = value;
    }

    @Override
    public String getName() {
        return "youngerThan_";
    }

    @Override
    public List<HumanDto> apply() {
        return EntitiesMapping.asDto(humanRepository.findByAgeLessThanEqual(age));
    }
}
