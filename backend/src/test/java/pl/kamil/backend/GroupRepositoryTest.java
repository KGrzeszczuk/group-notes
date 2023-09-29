package pl.kamil.backend;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.kamil.backend.entity.GroupNote;
import pl.kamil.backend.service.GroupNoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTest {

    @Autowired
    private GroupNoteService groupNoteService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<GroupNote> groups = groupNoteService.list();
        Assert.assertTrue(groups.size() > 0);
    }
}