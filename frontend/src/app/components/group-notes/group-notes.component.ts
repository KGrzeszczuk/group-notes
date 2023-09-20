import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { HttpClient } from '@angular/common/http';
import { Component, Input, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Group } from 'src/app/services/groups.service';
import { Note, NotesService } from 'src/app/services/notes.service';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../dialogs/confirm-dialog/confirm-dialog.component';
import { NoteDialogComponent } from '../dialogs/note-dialog/note-dialog.component';

@Component({
  selector: 'app-group-notes',
  templateUrl: './group-notes.component.html',
  styleUrls: ['./group-notes.component.css']
})
export class GroupNotesComponent {
  @Input() group!: Group;

  notesService!: NotesService;
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

  constructor(private http: HttpClient, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.notesService = new NotesService(this.http);
  }

  openEditNoteDialog(note: Note) {
    const dialogRef =
      this.dialog.open(NoteDialogComponent, {
        data: note,
      });

    dialogRef.afterClosed().subscribe(updatedNote => {
      if (!updatedNote) return;
      this.notesService.updateNote(updatedNote).subscribe(result => Object.assign(note, result));
    });
  }

  deleteNote(note: Note) {
    const title = `Potwierdź`;
    const message = `Jesteś pewien usunięcia notatki?`;

    const dialogData = new ConfirmDialogModel(title, message);

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      this.notesService.deleteNote(note).subscribe(() => {
        this.group.notes = this.group.notes.filter(d => d.id != note.id)
      });

    });
  }
}
