package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="department_application",schema = "master")
public class DepartmentApplication extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;
	
	@JoinColumn(name = "proposal_id", nullable = true)
	private Integer proposal_id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "application_id", nullable = true)
	@JsonIgnore
	private Applications applications;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ref_id", nullable = true)
	@JsonIgnore
	private ProponentApplications proponentApplications;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "clearance_history_id", nullable = true)
	private ClearanceHistory clearanceHistory;
	
	@Column(length=50,nullable = true)
	private String ip_address;
	
	@Column(name = "proposal_sequence")
	@JsonIgnore
	private Integer proposal_sequence;
	
	@Column(name = "status", nullable = true,length = 50)
	private String status;
	
	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_active;

	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_deleted;
	
	public DepartmentApplication() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public DepartmentApplication(ProponentApplications proponentApplications) {
		this.proposal_sequence= proponentApplications.getProposal_sequence();
		this.proposal_no=proponentApplications.getProposal_no();
		this.proposal_id=proponentApplications.getProposal_id();
		this.applications=proponentApplications.getApplications();
		this.status=proponentApplications.getLast_status();
		this.ip_address=proponentApplications.getIp_address();
	}
}
