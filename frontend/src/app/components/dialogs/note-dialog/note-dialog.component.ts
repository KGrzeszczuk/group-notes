import { Component, Inject, ViewChild } from '@angular/core';
import { FormGroupDirective } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Note } from 'src/app/services/notes.service';

@Component({
  selector: 'app-note-dialog',
  templateUrl: './note-dialog.component.html',
  styleUrls: ['./note-dialog.component.css']
})
export class NoteDialogComponent {
  @ViewChild('dialogFrom') dialogFrom!: FormGroupDirective; 
  note!: Note;
  
  constructor(private dialogRef: MatDialogRef<NoteDialogComponent>, @Inject(MAT_DIALOG_DATA) public oldNote: Note) {
    this.note = { ...oldNote };
    if (!this.note.title) this.note.title = ""
    if (!this.note.description) this.note.description = ""
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveNote() {
    if(this.dialogFrom.valid) this.dialogRef.close(this.note);
  }
}