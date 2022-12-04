package com.example.restfullapp.service;

import com.example.restfullapp.entity.Note;
import com.example.restfullapp.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    private NoteRepo repository;

    @Autowired
    public void setRepository(NoteRepo repository) {
        this.repository = repository;
    }

    public List<Note> findOldFirst() {
        return repository.findAllByOrderByDateAsc();
    }

    public List<Note> findOldFirstDoneTrue() {
        return repository.findAllByOrderByDoneDescDateAsc();
    }

    public List<Note> findOldFirstDoneFalse() {
        return repository.findAllByOrderByDoneAscDateAsc();
    }

    public List<Note> findNewFirst() {
        return repository.findAllByOrderByDateDesc();
    }

    public List<Note> findNewFirstDoneTrue() {
        return repository.findAllByOrderByDoneDescDateDesc();
    }

    public List<Note> findNewFirstDoneFalse() {
        return repository.findAllByOrderByDoneAscDateDesc();
    }

    public void saveNote(Note note) {
        repository.save(note);
    }

    public Note findNote(Long id) {
        return repository.getById(id);
    }

    public void deleteNote(Long id) {
        repository.deleteById(id);
    }

    public void editDoneField(boolean done, Note note) {
        if (note.isDone()) {
            note.setDone(false);
        } else note.setDone(true);
        repository.save(note);
    }

    public void editMessage(Long id, String text) {
        Note updated = repository.getById(id);
        updated.setText(text);
        updated.setDate(new Date());
        repository.save(updated);
    }

}
