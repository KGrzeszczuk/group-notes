package pl.kamil.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kamil.backend.entity.Note;
import pl.kamil.backend.repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> list() {
        return noteRepository.findAll();
    }
}
