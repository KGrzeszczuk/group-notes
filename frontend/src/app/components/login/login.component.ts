import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppSessionService } from 'src/app/services/app-session-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  odpowedz1: string = "brak";
  odpowedz2: string = "brak";
  
  credentials = { username: '', password: '' };

  constructor(private app: AppSessionService, private http: HttpClient, private router: Router) {
    this.app.authenticate(undefined, undefined);
  }

  authenticated() { return this.app.authenticated; }

  login() {
    this.app.authenticate(this.credentials, () => {
        console.log("LOGOANIE")
    });
    return false;
  }

  dane:string = "pusto"

  pobierzDane1() {
    // this.http.get<{id:string}>('http://localhost:8080/resource').subscribe(data => {
    //   this.odpowedz1 = data.id
    //   console.log(data.id)
    // });

    this.http.get<{token: string}>('token').subscribe(data => {
      console.log(data);
      const token = data['token'];
      this.http.get<string>('http://localhost:9000', {headers : new HttpHeaders().set('X-Auth-Token', token)})
        .subscribe(response => this.odpowedz1 = response);
    }, () => {});

  }

  pobierzDane2() {
    this.http.get<{id:string}>('http://localhost:8080/resource2').subscribe(data => {
      this.odpowedz2 = data.id
      console.log(data.id)
    });
  }
}
