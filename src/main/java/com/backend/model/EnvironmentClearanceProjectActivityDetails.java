package com.backend.model;

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
import com.backend.model.EcForm6V2.EcForm6ProjectDetailsV2;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_proj_activity_dtls", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EnvironmentClearanceProjectActivityDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EnvironmentClearence environmentClearence; 
	
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

	@Column(nullable = false)
	private boolean isDelete;

	EnvironmentClearanceProjectActivityDetails() {
		this.isDelete = false;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Activities getActivities() {
		return activities;
	}

	public void setActivities(Activities activities) {
		this.activities = activities;
	}

	public SubActivities getSubActivities() {
		return subActivities;
	}

	public void setSubActivities(SubActivities subActivities) {
		this.subActivities = subActivities;
	}

	public String getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getSubActivityId() {
		return subActivityId;
	}

	public void setSubActivityId(Integer subActivityId) {
		this.subActivityId = subActivityId;
	}

	
	
}
