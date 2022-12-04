package com.example.restfullapp.repository;

import com.example.restfullapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findAllByOrderByDateAsc();//Old First

    List<Note> findAllByOrderByDateDesc();//New first

    List<Note> findAllByOrderByDoneDescDateAsc();// True old first

    List<Note> findAllByOrderByDoneDescDateDesc(); //True new first

    List<Note> findAllByOrderByDoneAscDateAsc();//False old first

    List<Note> findAllByOrderByDoneAscDateDesc(); //False new first
}
