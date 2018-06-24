package org.firstvision.restapi.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public List<CompanyDTO> getAllCompany() {
		return null;
	}

	public List<CompanyDTO> getCompany(int companyId) {
		return null;
	}

	public void addCompany(CompanyDTO company) {
		companyRepository.save(new CompanyDTO());
		
	}

	public void updateCompany(CompanyDTO company, int companyId) {
		
	}

	public void deleteCompany(int companyId) {
		
	}

}
