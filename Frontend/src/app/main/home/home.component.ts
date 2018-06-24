import { Component, OnInit } from '@angular/core';
import { first } from "rxjs/operators";

import { User } from "../../user/user";
import { UserService } from "../../user/user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentUser: User;

  constructor(
    private userService: UserService
  ) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
  }

  deleteUser(userId: number) {
    this.userService.delete(userId).pipe(first());
  }

}
