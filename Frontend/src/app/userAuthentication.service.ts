import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { RestApI } from "./constants";

@Injectable({
  providedIn: 'root'
})
export class UserAuthenticationService {

  private loginUrl = RestApI.BASE_API_URL+ RestApI.LOGIN;
  private headers = new HttpHeaders().append('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  login(
    username: String,
    password:String
  ) {
    console.log("login request");
    return this.http.post<any>(this.loginUrl,
      {username: username, password: password},
    )
    .pipe(map(token => {
      console.log(token);
      console.log(this.loginUrl);
      if(token) {
        localStorage.setItem('token', JSON.stringify(token));
        console.log("Token recieved: "+ token);
      }
      return token;
    }));
  }

  logout() {
    localStorage.removeItem('token');
  }

}
