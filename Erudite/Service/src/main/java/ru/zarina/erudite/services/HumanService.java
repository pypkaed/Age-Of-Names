package ru.zarina.erudite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.entities.Human;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.Random;

@Service
public class HumanService {
    private final int MAXIMUM_AGE = 110;
    private final HumanRepository humanRepository;
    @Autowired
    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public HumanDto findHuman(String name) {
        var human = humanRepository.findById(name);

        if (human.isEmpty()) {
            return addHuman(name);
        }

        return EntitiesMapping.asDto(human.get());
    }
    public HumanDto addHuman(String name) {

        Random random = new Random();
        Human human = new Human(name, random.nextInt(MAXIMUM_AGE));

        humanRepository.save(human);

        return EntitiesMapping.asDto(human);
    }
}
