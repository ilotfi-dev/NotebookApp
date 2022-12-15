package com.example.restfullapp.controller;

import com.example.restfullapp.entity.Note;
import com.example.restfullapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {
    private NoteService service;
    private String filterMethod = "ALL";
    private String sortDateMethod = "DESC";

    @Autowired
    public void setService(NoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<Note> notes = filterAndSort();
        model.addAttribute("notes", notes);
        return "index";
    }

    @GetMapping("/new")
    public String newNote() {
        return "/new";
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
    public String editDoneStatus(@PathVariable Long id, @RequestParam(value = "done", required = false) boolean done) {
        Note note = service.findNote(id);
        service.editDoneField(done, note);
        return "redirect:/";
    }


    @PostMapping("/save")
    public String saveNote(@RequestParam String text) {
        service.saveNote(new Note(text));
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return "redirect:/";
    }

    private List<Note> filterAndSort() {
        List<Note> notes = null;
        switch (filterMethod) {
            case "ALL":
                switch (sortDateMethod) {
                    case "ASC" -> notes = service.findOldFirst();
                    case "DESC" -> notes = service.findNewFirst();
                }
                break;
            case "TRUE":
                switch (sortDateMethod) {
                    case "ASC" -> notes = service.findOldFirstDoneTrue();
                    case "DESC" -> notes = service.findNewFirstDoneTrue();
                }
                break;
            case "FALSE":
                switch (sortDateMethod) {
                    case "ASC" -> notes = service.findOldFirstDoneFalse();
                    case "DESC" -> notes = service.findNewFirstDoneFalse();
                }
                break;
        }
        return notes;
    }
}
