package pl.kamil.backend.dto;

import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;

public class TableDataDto {
    private int _page;
    private int _pageSize;
    private String _sortHeader;
    private String _direction;
    private String _globalFilter;


    public TableDataDto() {
        _page = 0;
        _pageSize = Integer.MAX_VALUE;
        _sortHeader = "id";
        _direction = "ASC";
        _globalFilter = "";
    }

    public int get_page() {
        return _page;
    }

    public void set_page(int page) {
        this._page = page;
    }

    public int get_pageSize() {
        return _pageSize;
    }

    public void set_pageSize(int limit) {
        this._pageSize = limit;
    }

    public String get_sortHeader() {
        return _sortHeader;
    }

    public void set_sortHeader(String sort) {
        this._sortHeader = sort;
        if (sort.isEmpty())
            this._sortHeader = "id";
    }

    public String get_direction() {
        return _direction;
    }

    public void set_direction(String order) {
        Optional<Direction> direction = Direction.fromOptionalString(order);
        if(!direction.isPresent()){
            this._direction = Direction.ASC.name();
            return;
        }
        this._direction = direction.get().name();
    }

    public String get_globalFilter() {
        return _globalFilter;
    }

    public void set_globalFilter(String globalFilter) {
        this._globalFilter = globalFilter;
    }

}
