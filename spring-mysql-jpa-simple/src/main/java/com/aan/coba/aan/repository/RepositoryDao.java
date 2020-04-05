package com.aan.coba.aan.repository;

import com.aan.coba.aan.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryDao extends JpaRepository<Person, Long> {

    @Query("select u from Person u where u.name like %?1%")
    public List<Person> findByName(String name);

    @Query(value = "SELECT * FROM person WHERE id = ?1", nativeQuery = true)
    Person findByIdAan(long id);

}
