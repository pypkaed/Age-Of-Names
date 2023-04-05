package ru.zarina.erudite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.entities.Human;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class HumanService {
    private final HumanRepository humanRepository;
    @Autowired
    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public HumanDto findHuman(String name) throws Exception {
        var human = humanRepository.findById(name);
        if (human.isEmpty()) {
            throw new Exception("Couldn't find entity with name " + name);
        }

        return EntitiesMapping.asDto(human.get());
    }
    public HumanDto addHuman(String name, Integer age) throws Exception {
        if (humanRepository.findById(name).isPresent()) {
            throw new Exception("Entity with name " + name + " already exists.");
        }

        if (age == null) {
            age = ThreadLocalRandom.current().nextInt(Human.MINIMUM_AGE, Human.MAXIMUM_AGE + 1);
        }

        Human human = new Human(name, age);

        humanRepository.save(human);

        return EntitiesMapping.asDto(human);
    }
}
