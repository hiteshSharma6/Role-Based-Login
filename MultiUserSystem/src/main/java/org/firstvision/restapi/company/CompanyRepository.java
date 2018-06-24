package org.firstvision.restapi.company;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<CompanyDTO, Integer>{
	
	List<CompanyDTO> findByCreatedBy(int userId);

}
