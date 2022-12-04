package com.example.restfullapp.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "note", schema = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean done;
    private Date date;

    public Note(String text) {
        this.text = text;
        this.date = new Date();
        this.done = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Note note = (Note) o;
        return id != null && Objects.equals(id, note.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
