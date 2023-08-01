package com.backend.model.ForestClearanceFormD;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_d_proposed_diversion_details", schema = "master")
public class FcFormDProposedDiversionDetails extends Auditable<Integer> {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer Id;
	
	@Column(nullable=true)
	private String unit;

	@Column(name = "toposheet_no", nullable = true, length = 50)
	private String toposheet_no;

	@Column(name = "state", nullable = true, length = 50)
	private String state;

	@Column(name = "district", nullable = true, length = 50)
	private String district;

	@Column(name = "division", nullable = true, length = 50)
	private String division;

	@Column(name = "village", nullable = true, length = 50)
	private String village;

	@Column(name = "plot_no", nullable = true, length = 20)
	private String plot_no;

	@Column(name = "sub_district", nullable = true, length = 50)
	private String sub_district;

	@Column(name = "range", nullable = true, length = 50)
	private String range;

	@Column(name = "present_owner", nullable = true, length = 50)
	private String present_owner;

	@Column(name = "area")
	private String area;

	@Column(nullable = true)
	private Integer division_code;

	@Column(nullable = true)
	private Integer state_code;

	@Column(nullable = true)
	private Integer village_code;

	@Column(nullable = true)
	private Integer sub_district_code;

	@Column(nullable = true)
	private Integer district_code;

	@Column(nullable = true)
	private Boolean manual_entry;
	
	@Column(nullable = true)
	private Double forest_area_length;

	@Column(nullable = true)
	private Double forest_area_total;

	@Column(nullable = true)
	private Double forest_area_width;

	@Column(nullable = true)
	private Double non_forest_area_length;

	@Column(nullable = true)
	private Double non_forest_area_total;

	@Column(nullable = true)
	private Double non_forest_area_width;

	@Column(nullable = true)
	private Double non_linear_forest_area;

	@Column(nullable = true)
	private Double non_linear_non_forest_area;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormDProposedDiversionDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
