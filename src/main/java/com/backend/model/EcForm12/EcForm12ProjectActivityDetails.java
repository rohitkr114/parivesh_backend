package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.Activities;
import com.backend.model.SubActivities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_proj_activity_dtls", schema = "master")
public class EcForm12ProjectActivityDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form12_id", nullable = true)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	private EcForm12 ecForm12;
	
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

	public EcForm12ProjectActivityDetails() {
		this.isDelete = false;
	}
	
	

}
