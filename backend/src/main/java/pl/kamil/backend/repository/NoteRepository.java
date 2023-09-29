package pl.kamil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.kamil.backend.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}