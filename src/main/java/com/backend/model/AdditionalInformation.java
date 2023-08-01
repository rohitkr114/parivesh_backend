package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="additional_information",schema = "master")
public class AdditionalInformation extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_additional_information", nullable = true)
	private DocumentDetails upload_additional_information;
	
	@Column(length = 50,nullable = true)
	private String ref_id;
	
	@Column(nullable = true,length=1000)
	private String remarks_additional_information;
	
	@Column(length = 100,nullable = true)
	private String document_name;
	
	@Column(nullable = true)
	private Integer clearance_id;
	
	@Column(nullable = true)
	private Integer process_step;
	
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "clearance_history_id", nullable = true)
//	private ClearanceHistory clearanceHistory;
	
	/*
	@ManyToOne
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;
	
	@ManyToOne
	@JoinColumn(name = "wlc_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;
	*/
	
	private Boolean is_active;
	
	private Boolean is_deleted;

	private Boolean is_special_document =false;
	
	AdditionalInformation(){
		is_active=true;
		is_deleted=false;
		is_special_document=false;
	}

}
