package ru.zarina.erudite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "humans")
@Getter
@Setter
@NoArgsConstructor

public class Human {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age")
    private Integer age;
    public Human(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
