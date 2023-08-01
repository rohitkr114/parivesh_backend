package com.backend.model.EcForm7;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form_7_minor_activity", schema = "master")
public class EcForm7MinorActivity extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

//	@ManyToOne
//	@JoinColumn(name = "ec_form_7_id", nullable = true)
//	@JsonIgnore
//	private EcForm7 ecForm7;

//	@Column(nullable = true)
//	private String schedule_item_no_minor_activity;
//	
//	@Column(nullable = true)
//	private Double capacity_minor_activity;

	@Column(nullable = true)
	private Integer activityId;
	
	@Column(nullable = true)
	private Integer subActivityId;
	
	@Column(nullable = true)
	private String threshold;
	
	@Column(nullable = true)
	private String activity_type;
	
	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;
	
	private EcForm7MinorActivity() {
		this.is_active=true;
		this.is_delete=false;
	}

	public EcForm7MinorActivity(Integer id, Integer activityId, Integer subActivityId, String threshold,
			String activity_type) {
		this.id = id;
		this.activityId = activityId;
		this.subActivityId = subActivityId;
		this.threshold = threshold;
		this.activity_type = activity_type;
	}

//	public EcForm7MinorActivity(Integer id, String schedule_item_no_minor_activity, Double capacity_minor_activity) {
//		this.id = id;
//		this.schedule_item_no_minor_activity = schedule_item_no_minor_activity;
//		this.capacity_minor_activity = capacity_minor_activity;
//	}
	
	
}
