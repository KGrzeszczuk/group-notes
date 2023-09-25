import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


export interface Credentials {
  username: string;
  password: string;
}

@Injectable()
export class AppSessionService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials: Credentials | undefined, callback: (() => void) | undefined) {

    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get<{ name: string }>('http://localhost:8080/user', { headers: headers }).subscribe(response => {
      if (response?.name) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });

  }

}