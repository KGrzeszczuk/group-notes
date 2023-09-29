package pl.kamil.backend.repository;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.kamil.backend.entity.GroupNote;
import pl.kamil.backend.entity.GroupNote_;

public class GroupNoteSpecification {

    public static Specification<GroupNote> globalFilter(String filter) {
        return new Specification<GroupNote>() {
            public Predicate toPredicate(Root<GroupNote> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(GroupNote_.name)),
                                "%" + filter.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(GroupNote_.description)),
                                "%" + filter.toLowerCase() + "%"));
            }

        };
    }
}
