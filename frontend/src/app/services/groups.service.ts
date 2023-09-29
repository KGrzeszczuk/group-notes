import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { AppSettings } from '../app-settings';
import { TableDataDto } from '../dto/table-data-dto';
import { Note } from './notes.service';

export interface Group {
  id: number;
  name: string;
  description: string;
  notes: Note[];
}

export interface GroupsApi {
  groups: Group[];
  totalCount: number;
}

@Injectable({
  providedIn: 'root'
})
export class GroupsService {
  constructor(private http: HttpClient) { }

  getGroupItems(tableDataDto: TableDataDto) {
    return this.http.post<Group[]>(`${AppSettings.API_ENDPOINT}/private/groups`, tableDataDto, { observe: 'response' }).pipe(
      map(
        data => {
          return {
            groups: data.body!,
            totalCount: Number(data.headers.get('X-Total-Count'))
          };
        }));
  }

  getGroup(groupId: number): Observable<Group> {
    const requestUrl = `${AppSettings.API_ENDPOINT}/private/group/${groupId}`;
    return this.http.get<Group>(requestUrl);
  }
}
