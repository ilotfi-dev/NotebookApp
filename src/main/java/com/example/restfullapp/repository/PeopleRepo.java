package com.example.restfullapp.repository;

import com.example.restfullapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PeopleRepo extends JpaRepository<Person, Long> {

    Optional<Person> findByLogin(String login);
}
