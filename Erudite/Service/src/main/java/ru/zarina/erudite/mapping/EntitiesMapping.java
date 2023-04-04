package ru.zarina.erudite.mapping;

import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.entities.Human;

public class EntitiesMapping {
    public static HumanDto asDto(Human human) {
        return new HumanDto(human.getName(), human.getAge());
    }
}
