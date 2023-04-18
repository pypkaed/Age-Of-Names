package ru.zarina.erudite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.entities.Human;
import ru.zarina.erudite.exceptions.HumanServiceException;
import ru.zarina.erudite.mapping.EntitiesMapping;
import ru.zarina.erudite.repositories.HumanRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HumanService {
    private final HumanRepository humanRepository;
    @Autowired
    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public HumanDto findHuman(String name) throws HumanServiceException {
        var human = humanRepository.findByNameIgnoreCase(name);
        if (human.isEmpty()) {
            throw HumanServiceException.EntityNotFound(name);
        }

        return EntitiesMapping.asDto(human.get());
    }
    public List<HumanDto> findAll() {
        var humans = humanRepository.findAll();
        return EntitiesMapping.asDto(humans);
    }
    public HumanDto addHuman(String name, Integer age) throws HumanServiceException {
        if (humanRepository.findByNameIgnoreCase(name).isPresent()) {
            throw HumanServiceException.EntityDuplicate(name);
        }

        if (age == null) {
            age = ThreadLocalRandom.current().nextInt(Human.MINIMUM_AGE, Human.MAXIMUM_AGE + 1);
        }

        Human human = new Human(name, age);
        humanRepository.save(human);

        return EntitiesMapping.asDto(human);
    }
}
