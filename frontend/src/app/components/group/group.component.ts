import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Group, GroupsService } from 'src/app/services/groups.service';
import { NotesService } from 'src/app/services/notes.service';
import { NoteDialogComponent } from '../dialogs/note-dialog/note-dialog.component';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent {
  groupsService!: GroupsService;
  notesService!: NotesService;
  group!: Group;

  constructor(private http: HttpClient, private route: ActivatedRoute, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.groupsService = new GroupsService(this.http);
    this.notesService = new NotesService(this.http);
    const id: number = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.groupsService.getGroup(id).subscribe(data => {
      this.group = data;
    })
  }

  openNewNoteDialog() {
    const dialogRef = this.dialog.open(NoteDialogComponent);

    dialogRef.afterClosed().subscribe(newNote => {
      if (!newNote) return;
      newNote.groupId = this.group.id;
      newNote.readed = false;
      this.notesService.addNote(newNote).subscribe(result => {
        this.group.notes.push(result);
      }
      );
    });
  }

}
