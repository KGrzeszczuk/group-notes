import { SortDirection } from "@angular/material/sort";

export class TableDataDto {
    private _page: string;
    private _pageSize: string;
    private _sortHeader: string;
    private _direction: SortDirection;
    private _globalFilter: string;

    constructor() {
        this._page = '0';
        this._pageSize = '10';
        this._sortHeader = 'id';
        this._direction = 'asc';
        this._globalFilter = '';
    }

    public get page(): string {
        return this._page;
    }

    public set page(value: string) {
        this._page = value;
    }

    public get pageSize(): string {
        return this._pageSize;
    }

    public set pageSize(value: string) {
        this._pageSize = value;
    }

    public get sortHeader(): string {
        return this._sortHeader;
    }

    public set sortHeader(value: string) {
        this._sortHeader = value;
        if (value == null) {
            this._sortHeader = ''
        }
    }

    public get direction(): SortDirection {
        return this._direction;
    }

    public set direction(value: SortDirection) {
        this._direction = value;
    }

    public get globalFilter(): string {
        return this._globalFilter;
    }

    public set globalFilter(value: string) {
        this._globalFilter = value;
    }
}
