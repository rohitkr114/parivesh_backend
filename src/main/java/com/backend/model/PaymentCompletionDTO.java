package com.backend.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


//@Entity
@Data
public class PaymentCompletionDTO {

	private String Proposal_no;
	
	private String Application_No;
	
	private Date Application_Date;
	
	private String Agency_name;
	
	private String Agency_Address;
	
	private String District;
	
	private String State;
	
	private String email;
	
	private String mobile;
	
	private String Telephone;
	
	private String Total_amount;
}
