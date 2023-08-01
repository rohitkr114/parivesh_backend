package com.backend.model.ForestClearanceE;

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
@Table(name="fc_form_e_prior_proposal",schema = "master")
public class FcFormEPriorProposal extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_e_id", nullable = true)
	@JsonIgnore
	private FcFormE fcFormE;
	
	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;
	
	@Column(nullable = true)
	private String moefcc_file_no;
	
	@Column(nullable = true)
	private Double area_forest_land_diversted;
	
	@Column(nullable = true)
	private Double period_of_diversion;
	
	@Column(nullable = true)
	private Date date_of_approval;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormEPriorProposal() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormEPriorProposal(Integer id) {
		this.id = id;
	}
	
}
