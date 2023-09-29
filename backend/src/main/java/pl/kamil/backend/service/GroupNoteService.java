package pl.kamil.backend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.persistence.EntityManager;
import pl.kamil.backend.dto.TableDataDto;
import pl.kamil.backend.entity.GroupNote;
import pl.kamil.backend.repository.GroupNoteRepository;
import pl.kamil.backend.repository.GroupNoteSpecification;

@Service
@RequestScope
public class GroupNoteService {

    @Autowired
    private GroupNoteRepository groupNoteRepository;

    @Autowired
    EntityManager em;
    
    private Page<GroupNote> page;
    private boolean isPageSet = false;

    public GroupNoteService() {
        page = new PageImpl<>(Collections.emptyList());
    }

    public List<GroupNote> list() {
        if(!isPageSet) setPage(null);
        return page.getContent();
    }

    public Long getTotalCount() {
        if(!isPageSet) setPage(null);
        return page.getTotalElements();
    }

    public void setPage(TableDataDto tableDataDto) {
        isPageSet = true;
        if (tableDataDto == null)
            tableDataDto = new TableDataDto();
        Pageable firstPageWithTwoElements = PageRequest.of(tableDataDto.get_page(), tableDataDto.get_pageSize(),
                Sort.by(Direction.fromString(tableDataDto.get_direction()), tableDataDto.get_sortHeader()));
        page = groupNoteRepository.findAll(GroupNoteSpecification.globalFilter(tableDataDto.get_globalFilter()),firstPageWithTwoElements);
    }

    public GroupNote getOne(Long id) {
        return groupNoteRepository.findById(id).get();
    }
}
