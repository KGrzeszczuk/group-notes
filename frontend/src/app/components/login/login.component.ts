import { HttpClient } from '@angular/common/http';
import { Component, NgZone } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppSessionService } from 'src/app/services/app-session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  odpowedz1: string = "brak";
  odpowedz2: string = "brak";

  credentials = { username: '', password: '' };

  constructor(private app: AppSessionService, private http: HttpClient, private router: Router, private _snackBar: MatSnackBar, private zone: NgZone) {
    // this.app.authenticate(undefined, undefined);
  }

  authenticated() { return this.app.authenticated; }

  login() {
    this.app.authenticated = true;
    this.http.post("http://localhost:8080/public/login", this.credentials).subscribe({
      next: data => {
        console.log("data")
        console.log(data)
      }
    })
  }

  dane: string = "pusto"

  pobierzDane1() {
    this.http.get<any>('http://localhost:8080/public/token').subscribe(data => {
      this.odpowedz1 = data.toString();
      console.log("data")
      console.log(data)
    });

    // this.http.get<{token: string}>('token').subscribe(data => {
    //   console.log(data);
    //   const token = data['token'];
    //   this.http.get<string>('http://localhost:9000', {headers : new HttpHeaders().set('X-Auth-Token', token)})
    //     .subscribe(response => this.odpowedz1 = response);
    // }, () => {});

  }

  pobierzDane2() {
    this.http.get<string>('http://localhost:8080/private/token').subscribe({
      next: data => {
        this.odpowedz2 = data
        console.log("data")
        console.log(data)
      }
      ,
      error: e => {
        console.log("zpaanay")
        console.log(e)
        throw e
      }
    })
  }
}
