package com.example.restfullapp.util;


import com.example.restfullapp.entity.Person;
import com.example.restfullapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(person.getLogin().isEmpty())
            errors.rejectValue("login", "", "Field is empty");

        if(personService.findPersonByName(person.getLogin()).isEmpty()){
            return;
        }

        errors.rejectValue("login", "", "Login already taken");
    }
}
