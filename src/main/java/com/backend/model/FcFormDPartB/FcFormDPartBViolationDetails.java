package com.backend.model.FcFormDPartB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.FcFormBPartB.FcFormBPartBBasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_d_part_b_violation_details", schema = "master")
public class FcFormDPartBViolationDetails  extends Auditable<Integer>  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_d_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormDPartBBasicDetails fcFormDPartBBasicDetails;

	@Column(length = 1000)
	private String violation_description;

	@Column(length = 10)
	private String work_period_to;

	@Column(length = 10)
	private String work_period_from;

	private Double forest_land_violation_area;

	@Column(length = 500)
	private String violation_owner_name;

	@Column(length = 1000, nullable = true)
	private String violation_address_owner;

	@Column(length = 1000)
	private String violation_action_against_owner;

	@Column(nullable = true)
	private Long cafId;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	FcFormDPartBViolationDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
