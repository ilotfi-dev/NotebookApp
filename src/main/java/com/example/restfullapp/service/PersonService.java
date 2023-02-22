package com.example.restfullapp.service;

import com.example.restfullapp.entity.Person;
import com.example.restfullapp.repository.PeopleRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PeopleRepo peopleRepo;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PeopleRepo peopleRepo, PasswordEncoder passwordEncoder) {
        this.peopleRepo = peopleRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<Person> findPersonByName(String name){
        return peopleRepo.findByLogin(name);
    }

    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepo.save(person);
    }
}
