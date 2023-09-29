package pl.kamil.backend.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.kamil.backend.entity.GroupNote;

//TODO usunąć tylko to były próby
@Component
public class GroupNoteRepositoryImpl implements CustomGroupNoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GroupNote> findGroupNotesWithNoteCount() {
        // List<GroupNote> groupNotes = new ArrayList<>();

        // @SuppressWarnings("unchecked")
        // List<Object[]> listOfObj = entityManager.createNativeQuery("""
        //         SELECT
        //             gn.id,
        //             gn.name,
        //             gn.description,
        //             COUNT(n.id) AS count
        //         FROM group_note gn
        //         LEFT JOIN note n ON gn.id = n.group_note_id
        //         GROUP BY
        //             gn.id,
        //             gn.name,
        //             gn.description
        //         """)
        //         .getResultList();

        // listOfObj.stream().forEach(obj -> {
        //     GroupNote groupNote = new GroupNote();
        //     groupNote.setId((Long) obj[0]);
        //     groupNote.setName((String) obj[1]);
        //     groupNote.setDescription((String) obj[2]);
        //     groupNote.setNotes(new HashSet<Note>());
        //     // groupNote.setCount((Long) obj[3]);
        //     groupNotes.add(groupNote);
        // });

        @SuppressWarnings("unchecked")

        List<GroupNote> listOfObj3 = entityManager.createNativeQuery("""
                SELECT
                    gn.id,
                    gn.name,
                    gn.description,
                    COUNT(n.id) AS noteCount
                FROM group_note gn
                LEFT JOIN note n ON gn.id = n.group_note_id
                GROUP BY
                    gn.id,
                    gn.name,
                    gn.description
                """,GroupNote.class)
                .getResultList();


        // List<GroupNote> listOfObj2 = entityManager.createQuery("""
        //         SELECT
        //             gn.id,
        //             gn.name,
        //             gn.description
        //         FROM group_note gn
        //         LEFT JOIN note n 
        //             """, GroupNote.class)
        //         .getResultList();

        // listOfObj2.get(0).getNotes();
        // listOfObj2.stream().forEach(obj -> {
        // GroupNote groupNote = new GroupNote();
        // groupNote.setID((Long) obj[0]);
        // groupNote.setName((String) obj[1]);
        // groupNote.setDescription((String) obj[2]);
        // groupNote.setNotes(new HashSet<Note>());
        // groupNote.setCount((Long) obj[3]);
        // groupNotes.add(groupNote);
        // });

        // List<GroupNote> test = entityManager.createNativeQuery("""
        // SELECT
        // gn.id,
        // gn.name,
        // gn.description,
        // COUNT(n.id) AS count
        // FROM group_note gn
        // LEFT JOIN note n ON gn.id = n.group_note_id
        // GROUP BY
        // gn.id,
        // gn.name,
        // gn.description
        // """, "ResultWithCount")
        // .getResultList();

        return listOfObj3;

    }
}
