import { Component, OnInit } from '@angular/core';
import { CompanyService } from "./company.service";
import { Router } from "@angular/router";
import { Company } from "./company";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  private companies: Array<Company>;
  private company: Company;

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit() {
    this.companyService.getAllCompany().subscribe(companies => {
      console.log(companies);
      this.companies = companies;
    }, err => {
      console.log(JSON.stringify(err.json()));
    })
  }

  getThisCompany(compnayId: number): void {
    this.companyService.getCompany(compnayId).subscribe(company => {
      console.log(company);
      this.company = company;
    }, err => {
      console.log(JSON.stringify(err.JSON));
    });
  }

}
