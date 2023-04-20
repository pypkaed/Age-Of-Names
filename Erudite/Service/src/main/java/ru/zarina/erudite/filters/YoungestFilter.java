package ru.zarina.erudite.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.entities.Human;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class YoungestFilter implements Filter {
    private final HumanRepository humanRepository;
    @Autowired
    public YoungestFilter(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }
    
    @Override
    public String getName() {
        return "youngest";
    }

    @Override
    public List<HumanDto> apply() {
        var humans = humanRepository.orderByYoungest();
        List<HumanDto> humanDtosResult = new ArrayList<>();

        Integer oldestAge = humans.get(0).getAge();

        for (Human human: humans) {
            if (human.getAge().equals(oldestAge)) {
                humanDtosResult.add(EntitiesMapping.asDto(human));
            }
        }

        return humanDtosResult;
    }
}
