package com.example.restfullapp.controller;

import com.example.restfullapp.entity.Note;
import com.example.restfullapp.entity.Person;
import com.example.restfullapp.security.PersonDetails;
import com.example.restfullapp.service.NoteService;
import com.example.restfullapp.service.PersonDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {
    private final NoteService service;
    private String filterMethod = "ALL";
    private String sortDateMethod = "DESC";


    public MainController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        List<Note> notes = filterAndSort(person);
        model.addAttribute("notes", notes);
        return "index";
    }
    @PostMapping("/save")
    public String saveNote(@RequestParam String text) {
        service.saveNote(new Note(text));
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newNote() {
        return "newNote";
    }

    @GetMapping("/filter/{filter}")
    public String filterChoose(@PathVariable String filter) {
        filterMethod = filter;
        return "redirect:/";
    }

    @GetMapping("/sort/{sortDate}")
    public String sortChoose(@PathVariable String sortDate) {
        sortDateMethod = sortDate;
        return "redirect:/";
    }

    @PostMapping("/update")
    public String editNote(@RequestParam Long id, @RequestParam String text) {
        service.editMessage(id, text);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editMessage(@PathVariable Long id, Model model) {
        Note note = service.findNote(id);
        model.addAttribute("note", note);
        return "edit";
    }


    @PostMapping("/doneChanger/{id}")
    public String editDoneStatus(@PathVariable Long id) {
        Note note = service.findNote(id);
        service.editDoneField(note);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return "redirect:/";
    }

    private List<Note> filterAndSort(Person person) {
        List<Note> notes = null;
        switch (filterMethod) {
            case "ALL":
                switch (sortDateMethod) {
                    case "ASC" -> notes = service.findOldFirst(person);
                    case "DESC" -> notes = service.findNewFirst(person);
                }
                break;
            case "TRUE":
                switch (sortDateMethod) {
                    case "ASC" -> notes = service.findOldFirstDoneTrue(person);
                    case "DESC" -> notes = service.findNewFirstDoneTrue(person);
                }
                break;
            case "FALSE":
                switch (sortDateMethod) {
                    case "ASC" -> notes = service.findOldFirstDoneFalse(person);
                    case "DESC" -> notes = service.findNewFirstDoneFalse(person);
                }
                break;
        }
        return notes;
    }
}
