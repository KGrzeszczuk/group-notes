import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Note } from 'src/app/services/groups.service';

@Component({
  selector: 'app-edit-note-dialog',
  templateUrl: './edit-note-dialog.component.html',
  styleUrls: ['./edit-note-dialog.component.css']
})
export class EditNoteDialogComponent {
  note!: Note;
  constructor(public dialogRef: MatDialogRef<EditNoteDialogComponent>, @Inject(MAT_DIALOG_DATA) public oldNote: Note) {
    this.note = { ...oldNote };
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  editNote() {
    this.dialogRef.close(this.note);
  }
}