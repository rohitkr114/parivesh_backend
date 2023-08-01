package com.backend.dto;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Organisation {
	
	private String OrgId;
	
	private String AccreditationNo;
	
	private String OrganisationName;
	
	private String OrganizationHead;
	
	private String Designation;
	
	private String Address;
	
	private String LandlineNo;
	
	private String MobileNo;
	
	private String EmailId;
	
	private String SectorsOfAccreditation;
	
	private String ValidityOfAccreditation;
	
	private String PanNo;
	
	private String Category;
	
	private String ValididityFlag;
	
	private List<Consultant> Individuals;

}
