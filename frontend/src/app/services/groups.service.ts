import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SortDirection } from '@angular/material/sort';
import { Observable, map } from 'rxjs';

export interface Group {
  id: number;
  name: string;
  description: string;
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

  getGroupItems(sort: string, order: SortDirection, pageSize: Number, page: number): Observable<GroupsApi> {
    const href = 'http://localhost:5000';
    let orderUrl = "";
    if(order !== "" && sort !== null ){
      orderUrl = `&_sort=${sort}&_order=${order}`
    }
    const requestUrl = `${href}/groups?_page=${page + 1}&_limit=${pageSize}${orderUrl}`;

    return this.http.get<Group[]>(requestUrl, { observe: 'response' }).pipe(
      map(
        data => {
          return {
            groups: data.body!,
            totalCount: Number(data.headers.get('X-Total-Count'))
          };
        }));
  }
}
