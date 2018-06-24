import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { RestApI } from "../../constants";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private CompanyApi = RestApI.BASE_API_URL+ RestApI.COMPANY;
  constructor(private http: HttpClient) { }

  getAllCompany(): Observable<any> {
    return this.http.get(this.CompanyApi);
  }

  getCompany(companyId: number): Observable<any> {
    return this.http.get(this.CompanyApi+ '/'+ companyId);
  }

}
