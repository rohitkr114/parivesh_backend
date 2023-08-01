package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_minor_activity", schema = "master")
public class EcForm12MinorActivity extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

//	@ManyToOne
//	@JoinColumn(name = "ec_form_12_id", nullable = true)
//	@JsonIgnore
//	private EcForm12 ecForm12;

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
	
	private EcForm12MinorActivity() {
		this.is_active=true;
		this.is_delete=false;
	}

	public EcForm12MinorActivity(Integer id, Integer activityId, Integer subActivityId, String threshold,
								 String activity_type) {
		this.id = id;
		this.activityId = activityId;
		this.subActivityId = subActivityId;
		this.threshold = threshold;
		this.activity_type = activity_type;
	}

//	public EcForm12MinorActivity(Integer id, String schedule_item_no_minor_activity, Double capacity_minor_activity) {
//		this.id = id;
//		this.schedule_item_no_minor_activity = schedule_item_no_minor_activity;
//		this.capacity_minor_activity = capacity_minor_activity;
//	}
	
	
}
