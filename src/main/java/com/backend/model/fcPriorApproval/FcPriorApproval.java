package com.backend.model.fcPriorApproval;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.model.District;
import com.backend.model.ProjectDetails;
import com.backend.model.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="fc_prior_approval", schema="master")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class FcPriorApproval {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String user_id;
	
	private String sw_proposal_no;
	
	@OneToOne
	@JoinColumn(name = "state_lgd_id",nullable = true)
	private State state_lgd_id;
	
	
	@OneToOne
	@JoinColumn(name="project", nullable=true)
	private ProjectDetails project;
	
	private String brief_description;
	
	@OneToOne
	@JoinColumn(name = "district_id_lgd",nullable = true)
	private District district_id_lgd;
	
	private String category;
	
	private String shape_of_the_project;
	
	private String total_cost_of_the_project;
	
	private String forest_land_area;
	
	private String non_forest_land_area;
	
	private String whether_r_and_r_involved;
	
	private String num_of_project_displaced_families;
	
	private String whether_alternative_examined;
	
	private String reason_thereof;
	
	private String whether_project_located_in_scheduled_area;
	
	private String whether_fra_2006_process_completed;
	
	private String date_of_submission_by_pp;
	
	private String proposal_status;
	
	private String fc_proposal_number;
	
	private String date_of_acceptance_by_dfo;
	
	private String submission_date;
	
	private Boolean submissionFlag;
	
	private Boolean processedFlag;
	
	@Column(name = "caf_id", nullable = true)
	private Integer caf_id;
	
	@Column(name = "fc_id", nullable = true)
	private Integer fc_id;
	
	public FcPriorApproval(){
		this.processedFlag = false;
	}
}
