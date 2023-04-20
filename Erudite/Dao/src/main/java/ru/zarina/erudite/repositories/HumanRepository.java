package ru.zarina.erudite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zarina.erudite.entities.Human;

import java.util.List;
import java.util.Optional;

@Repository
public interface HumanRepository extends JpaRepository<Human, String> {
    Optional<Human> findByNameIgnoreCase(String name);

    @Query("SELECT h FROM Human AS h ORDER BY h.age DESC")
    List<Human> orderByOldest();

    @Query("SELECT h FROM Human AS h ORDER BY h.age ASC")
    List<Human> orderByYoungest();

    List<Human> findByAgeGreaterThanEqual(Integer age);
    List<Human> findByAgeLessThanEqual(Integer age);

    @Query("SELECT h FROM Human AS h WHERE LENGTH(h.name) = ?1")
    List<Human> findByNameLength(Integer length);

    @Query("SELECT h FROM Human AS h WHERE LENGTH(h.name) >= ?1")
    List<Human> findByNameLengthGreaterThanEqual(Integer length);

    @Query("SELECT h FROM Human AS h WHERE LENGTH(h.name) <= ?1")
    List<Human> findByNameLengthLessThanEqual(Integer length);
}
