package com.backend.model.ForestClearanceFormB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_compliance_details", schema = "master")
public class FcFormBComplianceDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
	@Column(length = 50, nullable = true)
	private String proposal_no;
	
	@Column(length = 100, nullable = true)
	private String moef_file_no;
	
	@Column(length = 600, nullable = true)
	private String moef_condition;
	
	@Column(length = 2000, nullable = true)
	private String compliance_details;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBComplianceDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBComplianceDetails(Integer id, String proposal_no, String moef_file_no, String moef_condition,
			String compliance_details) {
		this.id = id;
		this.proposal_no = proposal_no;
		this.moef_file_no = moef_file_no;
		this.moef_condition = moef_condition;
		this.compliance_details = compliance_details;
	}
	
	
}
