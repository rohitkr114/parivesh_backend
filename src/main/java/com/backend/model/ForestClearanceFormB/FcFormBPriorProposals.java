package com.backend.model.ForestClearanceFormB;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_prior_proposals", schema = "master")
public class FcFormBPriorProposals extends Auditable<Integer> {
	
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
	
	@Column(nullable = true)
	private Double forest_land_area;
	
	@Column(nullable = true)
	private int diversion_period;
	
	@Column(nullable = true)
	private Date approval_date;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBPriorProposals() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBPriorProposals(Integer id, String proposal_no, String moef_file_no, Double forest_land_area,
			int diversion_period, Date approval_date) {
		this.id = id;
		this.proposal_no = proposal_no;
		this.moef_file_no = moef_file_no;
		this.forest_land_area = forest_land_area;
		this.diversion_period = diversion_period;
		this.approval_date = approval_date;
	}

	public FcFormBPriorProposals(Integer id, String proposal_no, String moef_file_no, Double forest_land_area,
			int diversion_period, Date approval_date, Boolean is_active, Boolean is_deleted) {
		this.id = id;
		this.proposal_no = proposal_no;
		this.moef_file_no = moef_file_no;
		this.forest_land_area = forest_land_area;
		this.diversion_period = diversion_period;
		this.approval_date = approval_date;
		this.is_active = is_active;
		this.is_deleted = is_deleted;
	}
	
	
}
