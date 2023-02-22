package com.example.restfullapp.service;

import com.example.restfullapp.entity.Person;
import com.example.restfullapp.repository.PeopleRepo;
import com.example.restfullapp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepo peopleRepo;


    @Autowired
    public PersonDetailsService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepo.findByLogin(username);
        if(person.isEmpty())
            throw new UsernameNotFoundException("Person not found!");

        return new PersonDetails(person.get());

    }
}
