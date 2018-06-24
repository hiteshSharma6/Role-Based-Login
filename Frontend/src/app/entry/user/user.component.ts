import { Component, OnInit } from '@angular/core';
import { UserService } from "./user.service";
import { User } from "./user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  private user: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser(this.user.userId).subscribe((user: User) => {
      console.log(user);
      this.user = user;
    })
  }

}
