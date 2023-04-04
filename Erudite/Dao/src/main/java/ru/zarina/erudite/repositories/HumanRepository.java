package ru.zarina.erudite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zarina.erudite.entities.Human;

@Repository
public interface HumanRepository extends JpaRepository<Human, String> {
}
