package pl.kamil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import pl.kamil.backend.entity.GroupNote;

@Repository
public interface GroupNoteRepository extends JpaRepository<GroupNote, Long>, CustomGroupNoteRepository, JpaSpecificationExecutor<GroupNote> {
}
