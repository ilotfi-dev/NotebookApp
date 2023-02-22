package com.example.restfullapp.service;

import com.example.restfullapp.entity.Note;
import com.example.restfullapp.entity.Person;
import com.example.restfullapp.repository.NoteRepo;
import com.example.restfullapp.security.PersonDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepo repository;

    public NoteService(NoteRepo repository) {
        this.repository = repository;

    }


    public List<Note> findOldFirst(Person person) {
        return repository.findAllByPersonOrderByDateAsc(person);
    }

    public List<Note> findOldFirstDoneTrue(Person person) {

        return repository.findAllByPersonOrderByDoneDescDateAsc(person);
    }

    public List<Note> findOldFirstDoneFalse(Person person) {
        return repository.findAllByPersonOrderByDoneAscDateAsc(person);
    }

    public List<Note> findNewFirst(Person person) {
        return repository.findAllByPersonOrderByDateDesc(person);
    }

    public List<Note> findNewFirstDoneTrue(Person person) {
        return repository.findAllByPersonOrderByDoneDescDateDesc(person);
    }

    public List<Note> findNewFirstDoneFalse(Person person) {
        return repository.findAllByPersonOrderByDoneAscDateDesc(person);
    }

    public void saveNote(Note note) {
        repository.save(note);
    }

    public Note findNote(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteNote(Long id) {
        repository.deleteById(id);
    }

    public void editDoneField(Note note) {
        note.setDone(!note.isDone());
        repository.save(note);
    }

    public void editMessage(Long id, String text) {
        Note updated = repository.findById(id).orElse(null);
        updated.setText(text);
        updated.setDate(new Date());
        repository.save(updated);
    }

}
