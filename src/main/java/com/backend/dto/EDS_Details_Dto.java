package com.backend.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.backend.model.Applications;
import com.backend.model.ClearanceHistory;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;

import lombok.Data;

@Data
@Entity
public class EDS_Details_Dto {
	
	@Id
	private Integer id;

	private Integer proponent_application_id;

	private String remarks;

	private DocumentDetails document;
	
	private String proposal_no;
	
	private Integer proposal_id;
	
	private Applications applications;
	
	private ProponentApplications proponentApplications;
	
	private ClearanceHistory clearanceHistory;
	
	private String ip_address;
	
	private Integer proposal_sequence;
	
	private String status;
}
