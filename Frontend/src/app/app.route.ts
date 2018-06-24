import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from "./app.component";
import { MainComponent } from './main/main.component';
import { LoginComponent } from './main/login/login.component';
import { RegisterComponent } from './main/register/register.component';
import { UserComponent } from "./entry/user/user.component";
import { CompanyComponent } from "./entry/user/company/company.component";
import { AuthGuard } from "./auth.guard";
import { DashboardComponent } from './entry/dashboard/dashboard.component';
import { HomeComponent } from "./main/home/home.component";
import { EntryComponent } from "./entry/entry.component";

const appRoutes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      }
    ]
  },
  {
    path: '',
    component: EntryComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: DashboardComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'company',
        component: CompanyComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user',
        component: UserComponent,
        canActivate: [AuthGuard]
      }
    ]
  },
  {
    path: '**',
    redirectTo: ''
  }
];

export const AppRoutes = RouterModule.forRoot(appRoutes);