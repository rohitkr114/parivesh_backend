package com.backend.model.ForestClearanceFormC;

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
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_prior_approval",schema = "master")
public class FcFormCPriorApproval extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;

	@Column(nullable = true)
	private String proposal_no;
	
	@Column(nullable = true)
	private String proposal_name;
	
	@Column(nullable = true)
	private String moefcc_file_no;
	
	@Column(nullable = true)
	private Double area_proposed_diversion;
	
	@Column(nullable = true)
	private Double area_diverted;
	
	@Column(nullable = true)
	private Double recommended_diversion_area;
	
	@Column(nullable = true)
	private Date in_principal_approval_date;
	
	@Column(nullable = true)
	private Date final_approval;
	
	@Column(nullable = true)
	private Date application_date;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormCPriorApproval() {
		this.is_active=true;
		this.is_delete=false;
	}
	
	public FcFormCPriorApproval(Integer id) {
		this.id=id;
	}

	public FcFormCPriorApproval(Integer id, String proposal_no, String proposal_name, String moefcc_file_no,
			Double area_proposed_diversion, Double area_diverted, Double recommended_diversion_area,
			Date in_principal_approval_date, Date final_approval, Date application_date) {
		super();
		this.id = id;
		this.proposal_no = proposal_no;
		this.proposal_name = proposal_name;
		this.moefcc_file_no = moefcc_file_no;
		this.area_proposed_diversion = area_proposed_diversion;
		this.area_diverted = area_diverted;
		this.recommended_diversion_area = recommended_diversion_area;
		this.in_principal_approval_date = in_principal_approval_date;
		this.final_approval = final_approval;
		this.application_date = application_date;
	}
	

}
