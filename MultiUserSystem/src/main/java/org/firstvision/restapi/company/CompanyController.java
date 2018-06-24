package org.firstvision.restapi.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/")
	public List<CompanyDTO> getCompanyDetails() {
		return companyService.getAllCompany();
	}

	@PostMapping("/")
	public void addCompany(
			@RequestBody CompanyDTO company
			) {
		companyService.addCompany(company);
	}
	
	@GetMapping("/{companyId}")
	public List<CompanyDTO> getCompany(
			@PathVariable int companyId
			) {
		return companyService.getCompany(companyId);
	}
	
	@PutMapping("/{companyId}")
	public void updateCompany(
			@RequestBody CompanyDTO company,
			@PathVariable int companyId
			) {
		companyService.updateCompany(company, companyId);
	}
	
	@DeleteMapping("/{companyId}")
	public void deleteCompany(
			@PathVariable int companyId
			) {
		companyService.deleteCompany(companyId);
	}

}
