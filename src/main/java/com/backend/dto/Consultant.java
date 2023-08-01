package com.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Consultant {
	
	private String IndividualId;
	
	private String NameOfConsultant;
	
	private String RoleOfIndividual;
	
	private String Engagement;
	
	private String PanNo;
	
	private String MobileNo;
	
	private String EmailId;
	
	private String Category;
	
	private String SectorsOfAccreditation;
	
	private String ValidityOfAccreditation;
	
	private String Address;
	
	private String ValididityFlag;
	
}
