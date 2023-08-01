package com.backend.model.ForestClearanceE;

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
@Table(name="fc_form_e_compliance",schema = "master")
public class FcFormECompliance extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;
	
	@Column(nullable = true)
	private String moefcc_file_no;
	
	@Column(nullable = true)
	private String condition_stipulated;
	
	@Column(nullable = true)
	private String details_of_compliance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_e_id", nullable = false)
	@JsonIgnore
	private FcFormE fcFormE;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormECompliance() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormECompliance(Integer id) {
		this.id = id;
	}
	
}
