package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FCEmailDtoImpl implements FcEmailDto {
	
	String clearance_type;
	String proposal_no;
	String project_name;
	String orgname;
	Double forest_land;
	String shape_of_project;
	String proposal_applied_for;
	String approval_granting_authority;
	String statename;
	String districtname;
	String location;
	String proposal_category;
	String application_status;
	String emailid;
	String mobilenumber;
	String meeting_start_date;
	String meeting_mode;
	String meeting_venue;
	
}
