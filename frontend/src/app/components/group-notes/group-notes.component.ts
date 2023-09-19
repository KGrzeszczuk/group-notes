import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { HttpClient } from '@angular/common/http';
import { Component, Input, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Group, GroupsService, Note } from 'src/app/services/groups.service';
import { EditNoteDialogComponent } from '../dialogs/edit-note-dialog/edit-note-dialog.component';

@Component({
  selector: 'app-group-notes',
  templateUrl: './group-notes.component.html',
  styleUrls: ['./group-notes.component.css']
})
export class GroupNotesComponent {
  @Input() group!: Group;

  groupService!: GroupsService;
  breakpointObserver = inject(BreakpointObserver);

  columnNumber$: Observable<number> = this.breakpointObserver.observe([Breakpoints.Medium, Breakpoints.Small, Breakpoints.XSmall])
    .pipe(
      map(value => {
        if (value.breakpoints[Breakpoints.Small] || value.breakpoints[Breakpoints.XSmall])
          return 1;
        if (value.breakpoints[Breakpoints.Medium])
          return 2
        return 3;
      }),
      shareReplay()
    );

  constructor(private http: HttpClient, public dialog: MatDialog) {
    this.groupService = new GroupsService(this.http);
   }

  openEditNote(note: Note) {
    const dialogRef = 
    this.dialog.open(EditNoteDialogComponent, {
      data: note,
    });

    dialogRef.afterClosed().subscribe(newNote => {
      if(!newNote) return;
      this.groupService.updateNote(newNote).subscribe(result => Object.assign(note, result));
    });
  }
}
