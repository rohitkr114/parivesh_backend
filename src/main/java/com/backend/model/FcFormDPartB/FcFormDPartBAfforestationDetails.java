package com.backend.model.FcFormDPartB;

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
@Table(name = "fc_form_d_part_b_afforestation_details", schema = "master")
public class FcFormDPartBAfforestationDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_d_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormDPartBBasicDetails fcFormDPartBBasicDetails;

	private Integer district_code;	

	private Double district_total_area;
	
	@Column(length = 100)
	private String district_name;
	
	@Column
	private Double district_ga_area;
	
	@Column
	private Double district_forest_area;

	@Column
	private Double total_forest_area;

	@Column
	private Integer no_approved_cases;
	
	@Column
	private Double forest_land_ca;
	
	@Column
	private Double non_forest_land_area;
	
	@Column(length = 50)
	private String afforestation_progress;
	
	@Column
	private Double forest_land_area;
	
	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;
	
	@Column(length = 50)
	private String from_mining_plan;
	
	@Column(length = 50)
	private String to_mining_plan;
	
	@Column(length = 50)
	private String from_mining_lease;
	
	@Column(length = 50)
	private String to_mining_lease;
	

	FcFormDPartBAfforestationDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
	

}
