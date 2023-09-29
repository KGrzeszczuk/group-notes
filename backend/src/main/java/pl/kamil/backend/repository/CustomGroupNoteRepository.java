package pl.kamil.backend.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import pl.kamil.backend.entity.GroupNote;

@NoRepositoryBean
public interface CustomGroupNoteRepository {

    public List<GroupNote> findGroupNotesWithNoteCount();
}