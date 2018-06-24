import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './main/login/login.component';
import { RegisterComponent } from './main/register/register.component';
import { FormComponent } from './form/form.component';
import { FormFieldComponent } from './form/form-field/form-field.component';
import { HeaderComponent } from './main/header/header.component';
import { FooterComponent } from './main/footer/footer.component';
import { UserService } from './user/user.service';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './main/home/home.component';
import { UserComponent } from './user/user.component';
import { CompanyComponent } from './user/company/company.component';
import { UserAuthenticationService } from "./userAuthentication.service";
import { JwtInterceptor } from "./jwt.interceptor";
import { AppRoutes } from "./app.route";
import { DashboardComponent } from './entry/dashboard/dashboard.component';
import { MainComponent } from './main/main.component';
import { EntryComponent } from './entry/entry.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    FormComponent,
    FormFieldComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    UserComponent,
    CompanyComponent,
    DashboardComponent,
    MainComponent,
    EntryComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutes
  ],
  providers: [
    UserAuthenticationService,
    AuthGuard,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
