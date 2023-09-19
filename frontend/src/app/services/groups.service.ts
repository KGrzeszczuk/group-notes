import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SortDirection } from '@angular/material/sort';
import { Observable, map } from 'rxjs';

export interface Group {
  id: number;
  name: string;
  description: string;
  notes: Note[];
}

export interface Note {
  id: number;
  title: string;
  description: string;
  readed: boolean;
  groupId: number;
}

export interface GroupsApi {
  groups: Group[];
  totalCount: number;
}

@Injectable({
  providedIn: 'root'
})
export class GroupsService {
  href:string = 'http://localhost:5000/groups';

  constructor(private http: HttpClient) { }

  getGroupItems(sort: string, order: SortDirection, pageSize: Number, page: number): Observable<GroupsApi> {
    let orderUrl = "";
    if (order !== "" && sort !== null) {
      orderUrl = `&_sort=${sort}&_order=${order}`
    }
    const requestUrl = `${this.href}?_page=${page + 1}&_limit=${pageSize}${orderUrl}&_embed=notes`;

    return this.http.get<Group[]>(requestUrl, { observe: 'response' }).pipe(
      map(
        data => {
          return {
            groups: data.body!,
            totalCount: Number(data.headers.get('X-Total-Count'))
          };
        }));
  }

  getGroup(groupId: number): Observable<Group> {
    const requestUrl = `${this.href}/${groupId}?_embed=notes`;
    return this.http.get<Group>(requestUrl);
  }
}
