package com.backend.model.EcForm7;

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
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "ec_form_7_proj_activity_dtls", schema = "master")
public class EcForm7ProjectActivityDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form7_id", nullable = true)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	private EcForm7 ecForm7;
	
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
	private String activity_type;

	@Column(nullable = true)
	private String threshold;
	
	private String proposalNo;

	@Column(nullable = true)
	private Boolean isDelete;

	public EcForm7ProjectActivityDetails() {
		this.isDelete = false;
	}
	
	

}
