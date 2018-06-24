import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';

import { RestApI } from "../constants";
import { User } from "../user/user";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = RestApI.BASE_API_URL+ RestApI.USER;

  constructor(private http: HttpClient) { }
  
  getUser(userId: number) {
    return this.http.get(this.userUrl+ '/'+ userId);
  }

  create(user: User) {
    return this.http.post(this.userUrl, user);
  }

  update(user: User) {
    return this.http.put(this.userUrl+ "/"+ user.userId, user);
  }

  delete(userId: number) {
    return this.http.delete(this.userUrl+ "/"+ userId);
  }

}
