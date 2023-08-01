package com.backend.model.ForestClearanceE;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.backend.model.ForestClearanceFormC.FcFormCProposedLand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_e_undertaking",schema = "master")
public class FcFormEUndertaking extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "i_agree", nullable = true)
	private Boolean i_agree;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "undertaking_fc_act_copy_id", nullable = true)
	private DocumentDetails undertaking_fc_act_copy;

	@Column(name = "undertaking_person_name", length = 100, nullable = true)
	private String undertaking_person_name;

	@Column(name = "undertaking_person_designation", length = 100, nullable = true)
	private String undertaking_person_designation;

	@Column(name = "undertaking_person_company", length = 255, nullable = true)
	private String undertaking_person_company;

	@Column(name = "undertaking_person_address", length = 255, nullable = true)
	private String undertaking_person_address;

	@Column(name = "undertaking_person_esign", length = 255, nullable = true)
	private String undertaking_person_esign;

	@Column(name = "undertaking_date", nullable = true)
	private Date undertaking_date;
	
	@Transient
	private Date submission_date;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_e_id", nullable = true)
	@JsonIgnore
	private FcFormE fcFormE;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormEUndertaking() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormEUndertaking(Integer id, Boolean i_agree, DocumentDetails undertaking_fc_act_copy,
			String undertaking_person_name, String undertaking_person_designation, String undertaking_person_company,
			String undertaking_person_address, String undertaking_person_esign, Date undertaking_date) {
		this.id = id;
		this.i_agree = i_agree;
		this.undertaking_fc_act_copy = undertaking_fc_act_copy;
		this.undertaking_person_name = undertaking_person_name;
		this.undertaking_person_designation = undertaking_person_designation;
		this.undertaking_person_company = undertaking_person_company;
		this.undertaking_person_address = undertaking_person_address;
		this.undertaking_person_esign = undertaking_person_esign;
		this.undertaking_date = undertaking_date;
	}

	public FcFormEUndertaking(Integer id) {
		this.id = id;
	}
	
	
}
