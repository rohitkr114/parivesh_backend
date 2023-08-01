package com.backend.model.FcFormBPartB;

import javax.persistence.CascadeType;
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
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_part_b_wl_specific_details", schema = "master")
public class FcFormBPartBWLSpecificDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_b_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormBPartBBasicDetails fcFormBPartBBasicDetails;

	@Column(length = 1000, nullable = false)
	private String wl_details;

	@Column(nullable = false)
	private Boolean part_of_wl_area;

	@Column(length = 1000, nullable = true)
	private String wl_area_impact_description;

	@Column(nullable = true)
	private Boolean located_in_esz;

	@Column(nullable = true, length = 255)
	private String located_within;

	@Column(length = 1000, nullable = true)
	private String esz_impact_description;

	@Column(nullable = false)
	private Boolean is_part_of_wl_area_1_km;

	@Column(length = 1000, nullable = true)
	private String wl_area_1_km_impact_description;

	@Column(nullable = false)
	private Boolean fnf_found;

	@Column(length = 1000, nullable = true)
	private String fnf_impact_description;

	@Column(nullable = false)
	private Boolean any_monument_located;

	@Column(length = 1000, nullable = true)
	private String monument_located_description;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_noc_id", nullable = true)
	private DocumentDetails copy_of_noc;

	@Column(nullable = false)
	private Boolean unavoidable_land_requirment;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBPartBWLSpecificDetails() {
		this.is_active = true;
		this.is_deleted = false;
		this.unavoidable_land_requirment = false;
		this.any_monument_located = false;
		this.fnf_found = false;
		this.is_part_of_wl_area_1_km = false;
		this.located_in_esz = false;
		this.part_of_wl_area = false;
	}

}
