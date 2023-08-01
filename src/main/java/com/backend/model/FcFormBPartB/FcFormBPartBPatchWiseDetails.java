package com.backend.model.FcFormBPartB;

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
import com.backend.model.ForestClearancePartB.ForestClearanceBPatches;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_part_b_patch_wise_details", schema = "master")
public class FcFormBPartBPatchWiseDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fc_patch_id", nullable = true)
//	@JsonIgnore
//	private FcFormBPartBPatches fcFormBPartBPatches;

	private String patch_no;

	private Integer district_code;

	@Column(length = 100)
	private String district_name;

	@Column(length = 100)
	private String sub_district;

	@Column(length = 100,nullable = true)
	private String ca_land_category;

	private Double area;

	@Column(length = 100)
	private String pf_rf_name;

	@Column(length = 100)
	private String range;

	@Column(length = 100)
	private String village_name;

	@Column(length = 100)
	private String survey_no;

	@Column(length = 1000)
	private String plot_no;

	@Column(length = 100)
	private String site_name;

	@Column(nullable = true)
	private Integer division_id;

	@Column(length = 50, nullable = true)
	private String division_name;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	FcFormBPartBPatchWiseDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
