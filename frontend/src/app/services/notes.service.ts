import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

export interface Note {
  id: number;
  title: string;
  description: string;
  readed: boolean;
  groupId: number;
}

@Injectable({
  providedIn: 'root'
})
export class NotesService {
  baseUrl:string = 'http://localhost:5000/notes';

  constructor(private http: HttpClient) { }

  addNote(note: Note): Observable<Note> {
    return this.http.post<Note>(this.baseUrl, note);
  }

  updateNote(note: Note): Observable<Note> {
    const url = `${this.baseUrl}/${note.id}`;
    return this.http.put<Note>(url, note);
  }
  
  deleteNote(note: Note): Observable<Note> {
    const url = `${this.baseUrl}/${note.id}`;
    return this.http.delete<Note>(url);
  }
}

