package com.backend.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ListOrganisationsDto {

	private String org_id;
	
	private Integer id;
	
	private String Accreditation_No;
	
	private String name;
	
	private String designation;
	
	private String address;
	
	private String landline_no;
	
	private String mobile_no;
	
	private String email;
	
	private String accreditation_sectors;
	
	private Date accreditation_validity;
	
	private String pan;
	
	private String category;
	
	private String validity_flag;
	
	private String district;
	
	private String head;
	
	private String pincode;
	
	private String state_ut;
	
	private String vers = null;
	
	private String website;

	public ListOrganisationsDto(
			String Accreditation_No,
			String accreditation_sectors,
			Date accreditation_validity, 
			String address,
			String category,
			String designation, 
			String district,
			String email, 
			String head, 
			Integer id,
			String landline_no,
			String mobile_no, 
			String name,  
			String org_id, 
			String pan,  
			String pincode, 
			String state_ut, 
			String validity_flag, 
			String website) {
		if(org_id == null)
			this.org_id = null;
		else
			this.org_id = org_id;
		if(Accreditation_No == null)
			this.Accreditation_No = null;
		else
			this.Accreditation_No = Accreditation_No;
		if(name == null)
			this.name = null;
		else
			this.name = name;
		if(designation == null)
			this.designation = null;
		else 
			this.designation = designation;
		if(address == null)
			this.address = null;
		else
			this.address = address;
		if(landline_no == null)
			this.landline_no = null;
		else
			this.landline_no = landline_no;
		if(mobile_no == null)
			this.mobile_no = null;
		else
			this.mobile_no = mobile_no;
		if(email == null)
			this.email = null;
		else
			this.email = email;
		if(accreditation_sectors == null)
			this.accreditation_sectors = null;
		else
			this.accreditation_sectors = accreditation_sectors;
		if(accreditation_validity == null)
			this.accreditation_validity = null;
		else
			this.accreditation_validity = accreditation_validity;
		if(pan == null)
			this.pan = null;
		else
			this.pan = pan;
		if(category == null)
			this.category = null;
		else
			this.category = category;
		if(validity_flag == null)
			this.validity_flag = null;
		else
			this.validity_flag = validity_flag;
		if(district == null)
			this.district = null;
		else
			this.district = district;
		if(head == null)
			this.head = null;
		else
			this.head = head;
		if(pincode == null)
			this.pincode = null;
		else
			this.pincode = pincode;
		if(state_ut == null)
			this.state_ut = null;
		else
			this.state_ut = state_ut;
		this.vers = null;
		if(website == null)
			this.website = null;
		else
			this.website = website;
		if(id == null)
			this.id = null;
		else
			this.id = id;
	}
	
	
}
