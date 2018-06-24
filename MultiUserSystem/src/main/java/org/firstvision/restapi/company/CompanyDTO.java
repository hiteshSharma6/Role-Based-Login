package org.firstvision.restapi.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.firstvision.restapi.user.UserDTO;

@Entity
@Table(name = "company", schema = "visionfirstdb")
public class CompanyDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@ManyToOne(targetEntity = UserDTO.class, optional = false)
	@JoinColumn(name = "created_by")
	private int createdBy;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "approved")
	private boolean approved;

	public CompanyDTO() {
		super();
	}

	public CompanyDTO(int companyId, String companyName, int createdBy, String address, boolean approved) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.createdBy = createdBy;
		this.address = address;
		this.approved = approved;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "CompanyDTO [companyId=" + companyId + ", companyName=" + companyName + ", createdBy=" + createdBy
				+ ", address=" + address + ", approved=" + approved + "]";
	}

}
