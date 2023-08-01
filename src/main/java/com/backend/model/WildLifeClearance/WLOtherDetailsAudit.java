package com.backend.model.WildLifeClearance;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceProposedDiversions;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "wl_other_details_audit", schema = "master")
public class WLOtherDetailsAudit extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 20,nullable = true)
	private Boolean for_investigation_survey;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "location_and_map_id", nullable = true)
	private DocumentDetails location_and_map;
	
	@Column(length = 20,nullable = true)
	private String extent_of_physical_disturbance;
	
	private Double no_of_trees_proposed_to_be_cut;
	
	private Double earthwork;
	
	private Integer earthwork_timerequired;
	
	@Column(nullable = true)
	private Integer treecutting_timerequired;
	
	/*
	 * WLOtherDetails
	 */
	
	@Column(name="is_project_require_clearance_under_ec_act",nullable = true)
	private Boolean is_project_require_clearance_under_ec_act;
	
	@Column(name="status_ec_clearance",nullable = true)
	private String status_ec_clearance;
	
	@Column(nullable = true, length = 50)
	private String proposalNo;
	
	@Column(nullable = true)
	private Date issue_of_ec;
	
	@Column(nullable = true, length = 50)
	private String moefccFileNo;

	//upload EC Letter
	
	@Column(nullable = true)
	private Date application;
	
	@Column(nullable = true) 
	private String status_of_application;
	
	@Column(length = 1000,nullable = true)
	private String application_reason;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_letter_id", nullable = true)
	private DocumentDetails ec_letter;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_id", nullable = true)
	@JsonIgnore
	private WildLifeClearanceAudit wildLifeClearanceaudit;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;

		
}
