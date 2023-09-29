package pl.kamil.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.kamil.backend.dto.TableDataDto;
import pl.kamil.backend.entity.GroupNote;
import pl.kamil.backend.service.GroupNoteService;

@RestController
@RequestMapping("/private")
public class GroupNoteController {

    @Autowired
    private GroupNoteService groupNoteService;

    @PostMapping("/groups")
    public ResponseEntity<List<GroupNote>> getGroups(@RequestBody(required = false) TableDataDto tableDataDto) {
        groupNoteService.setPage(tableDataDto);
        List<GroupNote> groupNotes = groupNoteService.list();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", Long.toString(groupNoteService.getTotalCount()));
        return new ResponseEntity<List<GroupNote>>(groupNotes, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupNote> getGroup(@PathVariable Long id) {
        GroupNote groupNote = groupNoteService.getOne(id);
        return new ResponseEntity<GroupNote>(groupNote, HttpStatus.OK);
    }
}
