package ru.zarina.erudite.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Pattern;

@Entity
@Table(name = "humans")
@Getter
@Setter
@NoArgsConstructor

public class Human {
    private static final String HUMAN_NAME_PATTERN = "^[A-Za-z]+$";
    public static final int MAXIMUM_AGE = 110;
    public static final int MINIMUM_AGE = 1;
    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "age")
    private Integer age;

    public Human(String name, Integer age) {
        validateName(name);
        if (age < MINIMUM_AGE || age > MAXIMUM_AGE) {
            throw new IllegalArgumentException("Age should be in range (" + MINIMUM_AGE + ", " + MAXIMUM_AGE + ")");
        }

        this.name = name;
        this.age = age;
    }

    private void validateName(String name) {
        if (name == null)
            throw new NullPointerException();
        if (!Pattern.matches(HUMAN_NAME_PATTERN, name))
            throw new IllegalArgumentException("Name " + name + " should match the pattern " + HUMAN_NAME_PATTERN);
    }
}
