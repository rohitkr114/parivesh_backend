package com.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CrzDashboardDto {
	
//	-----------------------------ap
	@Id
	private Integer id;
	
	private Integer application_id;
	
	private String proposal_no;
	
	private String status;
	
	private String project_name;
	
	private String workgroup_name;
	
	private String officename;

	private String rolename;
	
	private Integer current_step_id;

	private Integer process_step_mapping_id;
	
	private Integer role_id;

	private Integer office_id;
	
	private Date start_date;
	
	@Column(nullable = true, length = 3000)
	private String proposal_json;
	
//	private Integer workgroup_id;
//	
	private Integer process_id;
//	
	private String other_property;
	
	private String workgroup_id;
	
	private Integer main_state;
	
	private Integer main_district;
	
	private String state_name;
	
	private String district_name;
	
	private String name_of_proponent;
//	
//	private Integer version;
//
//	
//
//	private Date end_date;
//
//	private Boolean is_current_step;
//	
//	private String remarks;
////	-------------------------------ps
//	private Integer app_history_id;
//	
//
//	private Integer user_id;
//
//	private Integer action_id;
//
//	private String authority_status;
//	
//	private Date authority_date;
////	--------------------------------pa
//	private Integer proposal_id;
////	--------------------------------pd
////	--------------------------------ro
////	--------------------------------we
////	--------------------------------app
//	private String appliocation_name;
////	--------------------------------oe
	
}
