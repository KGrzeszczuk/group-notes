package pl.kamil.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.kamil.backend.entity.Note;
import pl.kamil.backend.service.NoteService;

@RestController
@RequestMapping("/private")
public class NoteController {
    
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getGroups(){
        return noteService.list();
    }
}
