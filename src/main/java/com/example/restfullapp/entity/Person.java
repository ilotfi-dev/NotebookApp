package com.example.restfullapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user")
public class Person {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long user_id;
    @NotEmpty(message = "Name is empty")
    @Size(min=2, max = 100, message = "Username must be from 2 to 100 characters")
    private String login;
    private String password;
    @OneToMany(mappedBy = "person")
    private List<Note> notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return user_id != null && Objects.equals(user_id, person.user_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
