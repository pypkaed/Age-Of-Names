package ru.zarina.erudite.filters;

import ru.zarina.erudite.dtos.HumanDto;

import java.util.List;

public interface Filter {
    String getName();
    List<HumanDto> apply();
}
