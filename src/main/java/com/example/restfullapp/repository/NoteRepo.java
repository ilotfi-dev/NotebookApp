package com.example.restfullapp.repository;

import com.example.restfullapp.entity.Note;
import com.example.restfullapp.entity.Person;
import com.example.restfullapp.security.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    List<Note> findAllByPersonOrderByDateAsc(Person person);//Old First

    List<Note> findAllByPersonOrderByDateDesc(Person person);//New first

    List<Note> findAllByPersonOrderByDoneDescDateAsc(Person person);// True old first

    List<Note> findAllByPersonOrderByDoneDescDateDesc(Person person); //True new first

    List<Note> findAllByPersonOrderByDoneAscDateAsc(Person person);//False old first

    List<Note> findAllByPersonOrderByDoneAscDateDesc(Person person); //False new first
}
