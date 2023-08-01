package com.backend.model.CrzFactsheet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Entity
@Data
@Table(name  = "crz_factsheet", schema = "master")
public class CrzFactsheet extends Auditable<Integer> {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "crz_id")
	private Integer crz_id;
	
	@Column(name= "project_benefit", nullable=true, length=500)
	private String project_benefit;
	
	@Column(name="organization_name", nullable=true, length=255)
	private String organization_name;
	
	@Column(name = "project_details", nullable=true, length=500)
	private String project_details;
	
	@Column(name = "village", nullable=true)
	private String village;
	
	@Column(name = "sub_district", nullable=true)
	private String sub_district;
	
	@Column(name = "district", nullable=true)
	private String district;
	
	@Column(name = "state", nullable=true)
	private String state;
	
	@Column(name="state_name", nullable=true)
	private String state_name;
	
	@Column(name="district_name", nullable=true)
	private String district_name;
	
	@Column(name = "investment_proposed", nullable=true)
	private String investment_proposed;
	
	@Column(name="total_employment", nullable=true)
	private Long total_employment;
	
	@Column(name="prev_eac_appraised_on", nullable=true)
	private Date prev_eac_appraised_on;
	
	@Column(name="eac_final_recommendation_date", nullable=true)
	private Date eac_final_recommendation_date;
	
	@Column(name="eac_final_recommendation", nullable=true, length=100)
	private String eac_final_recommendation;
	
	@Column(name="eac_secretary_name", nullable=true, length=255)
	private String eac_secretary_name;
	
	@Column(name="public_hearing_issue", nullable=true, length=500)
	private String public_hearing_issue;
	
	@Column(name="court_pendency", nullable=true, length=500)
	private String court_pendency;
	
	@Column(name="additional_information", nullable=true, length=500)
	private String additional_information;
	
	@Column(name="major_controversy", nullable=true, length=500)
	private String major_controversy;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplications;
	
	@Column(name = "is_active")
	private Boolean is_active;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public CrzFactsheet() {
		
		is_active = true;
		is_deleted = true;
	}
}
