package com.backend.model.EcForm8TransferOfTOR;

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

import com.backend.model.Activities;
import com.backend.model.SubActivities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_8_proj_activity_dtls",schema = "master")
public class EcForm8ProjectActivityDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form8_id", nullable = true)
	@JsonIgnore
	private EcForm8TransferOfTOR ecForm8TransferOfTOR; 
	
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
	
	@Column(name="proposal_no",nullable =true)
	private String proposalNo;

	@Column(nullable = true)
	private boolean isDelete;

	public EcForm8ProjectActivityDetails() {
		this.isDelete = false;
	}

}
