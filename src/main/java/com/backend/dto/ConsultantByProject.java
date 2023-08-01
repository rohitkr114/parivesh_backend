package com.backend.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ConsultantByProject {
 
	private String accrediation_sectors;
	
	private Date accrediation_validity; 
	
	private String org_id;
	
	private String name; 
	
	private String email; 
	
	private String category; 
	
	private String address;
	
	private String mobile_no;
	
	public ConsultantByProject(String accrediation_sectors, Date accrediation_validity, String org_id, String name, String email, String catagory, String address, String mobile_no) {
		this.accrediation_sectors = accrediation_sectors;
		this.accrediation_validity = accrediation_validity;
		this.address = address;
		this.category = catagory;
		this.email = email;
		this.name = name;
		this.org_id = org_id;
		this.mobile_no = mobile_no;
	}
}
