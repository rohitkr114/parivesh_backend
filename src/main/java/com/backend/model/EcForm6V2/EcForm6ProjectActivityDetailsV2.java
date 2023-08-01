package com.backend.model.EcForm6V2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.model.Activities;
import com.backend.model.SubActivities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form6_project_activity_details", schema = "master")
public class EcForm6ProjectActivityDetailsV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id; 
	
	@Transient
	private Integer activityId;
	
	@Transient
	private Integer subActivityId;

	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activities activities;

	@ManyToOne
	@JoinColumn(name = "subactivity_id")
	private SubActivities subActivities;

	@Column(nullable = true)
	String activity_type;

	@Column(nullable = true)
	String threshold;
	
	String proposalNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form6_id", nullable = true)
	@JsonIgnore
	private EcForm6ProjectDetailsV2 ecForm6;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
}
