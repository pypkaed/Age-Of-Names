package ru.zarina.erudite.mapping;

import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.entities.Human;

import java.util.ArrayList;
import java.util.List;

public class EntitiesMapping {
    public static HumanDto asDto(Human human) {
        return new HumanDto(human.getName(), human.getAge());
    }
    public static List<HumanDto> asDto(List<Human> humans) {
        List<HumanDto> humanDtos = new ArrayList<>();

        for (Human human : humans) {
            humanDtos.add(asDto(human));
        }

        return humanDtos;
    }
}
