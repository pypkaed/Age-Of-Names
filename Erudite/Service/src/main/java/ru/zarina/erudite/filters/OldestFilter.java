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
public class OldestFilter implements Filter {
    private final HumanRepository humanRepository;
    @Autowired
    public OldestFilter(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public String getName() {
        return "oldest";
    }

    @Override
    public List<HumanDto> apply() {
        var humans = humanRepository.orderByOldest();
        List<HumanDto> humanDtosResult = new ArrayList<>();

        Integer youngestAge = humans.get(0).getAge();

        for (Human human: humans) {
            if (human.getAge().equals(youngestAge)) {
                humanDtosResult.add(EntitiesMapping.asDto(human));
            }
        }

        return humanDtosResult;
    }
}
