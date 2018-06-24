import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { UserService } from "../../user/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css', '../modal-form.common.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
  }

  get f() {
    return this.registerForm.controls;
  }

  register() {
    this.submitted = true;

    if(this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.userService.create(
      this.registerForm.value,
    )
    .pipe(first())
    .subscribe(
      data => {
        this.router.navigate(['/login']);;
      },
      error => {
        this.loading = false;
      }
    )
  }

}
