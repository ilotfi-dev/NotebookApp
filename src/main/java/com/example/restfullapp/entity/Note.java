package com.example.restfullapp.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.restfullapp.security.PersonDetails;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean done;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Person person;

    public Note(String text) {
        this.text = text;
        this.date = new Date();
        this.done = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        this.person = personDetails.getPerson();
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
