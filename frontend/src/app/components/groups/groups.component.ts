import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map, merge, startWith, switchMap } from 'rxjs';
import { TableDataDto } from 'src/app/dto/table-data-dto';
import { Group, GroupsService } from 'src/app/services/groups.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  groupService!: GroupsService | null;
  data!: Group[];

  resultsLength = 0;
  isLoadingResults = true;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'description', 'actions'];

  constructor(private http: HttpClient) { }

  ngAfterViewInit(): void {
    this.groupService = new GroupsService(this.http);

    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;

          let tableDataDto =  new TableDataDto();
          tableDataDto.page = this.paginator.pageIndex.toString();
          tableDataDto.pageSize = this.paginator.pageSize.toString();
          tableDataDto.direction = this.sort.direction;
          tableDataDto.sortHeader = this.sort.active;

          return this.groupService!.getGroupItems(tableDataDto);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;

          if (data === null) {
            return {
              groups: [],
              totalCount: 0
            };
          }          
          // Only refresh the result length if there is new data. In case of rate
          // limit errors, we do not want to reset the paginator to zero, as that
          // would prevent users from re-triggering requests.
          this.resultsLength = data.totalCount;
          return data;
        }),
      )
      .subscribe(data => {
        this.data = data.groups;
        this.resultsLength = data.totalCount;
      });
  }
}
