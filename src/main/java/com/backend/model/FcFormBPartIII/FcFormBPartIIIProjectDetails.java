package com.backend.model.FcFormBPartIII;

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

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_B_part_III_project_details", schema="authority")
public class FcFormBPartIIIProjectDetails extends Auditable<Integer> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Integer project_id;

	@Column(name = "total_forest_area_applied")
	private Double totalForestAreaApplied;

	@Column(name = "deliberated_at_PSC")
	private boolean deliberatedAtPSC;

	@Column(name = "recommendation_status")
	private String recommendationStatus;

	@Column(name = "recommended_area")
	private Double recommendedArea;

	@Column(name = "forest_comes_under_LWEOFCLAC")
	private boolean forestComesUnderLWEOFCLAC;
	
	@Column(name="recommendation_details")
	private String recommendationDetails;

	@Column(name = "justification",length=5000)
	private String justification;
	
	private Boolean is_active;

	private Boolean is_deleted;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="formB_partIII_checklistDetails_id",nullable = false)
	@JsonIgnore
	private FcFormBPartIIICheckListDetails formBPartIIIChecklistDetails; 

	public FcFormBPartIIIProjectDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
