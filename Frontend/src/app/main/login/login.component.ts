import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators, FormControlName, FormControl } from "@angular/forms";
import { first } from "rxjs/operators";

import { UserAuthenticationService } from "../../userAuthentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../modal-form.common.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userAuthentication: UserAuthenticationService
  ) { }

  ngOnInit() {
    this.userAuthentication.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
    console.log(this.returnUrl);
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    })
  }

  get f() {
    return this.loginForm.controls;
  }

  login() {
    this.submitted = true;

    if(this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.userAuthentication.login(
      this.f.username.value,
      this.f.password.value
    )
    .pipe(first())
    .subscribe(token => {
        console.log(token);
        console.log(this.returnUrl)
        this.router.navigate([this.returnUrl]);
      },
      error => {
        console.error("error: "+error);
        this.loading = false;
      }
    )
  }

}
