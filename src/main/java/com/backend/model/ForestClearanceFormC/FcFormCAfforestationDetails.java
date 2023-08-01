package com.backend.model.ForestClearanceFormC;

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
@Table(name="fc_form_c_afforestation_details",schema = "master")
public class FcFormCAfforestationDetails extends Auditable<Integer>{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String comp_afforestation_type;

	@Column(nullable=true)
	private Boolean is_applicable_compensatory_afforestation;
	
	@Column(nullable=true)
	private Boolean is_non_forest_land;
	
	@Column(nullable = true)
	private Integer total_patches;

	@Column(nullable = true)
	private Integer total_districts_involved_in_ca;

	@Column(nullable = true)
	private Boolean is_ua_land_smaller_from_proposed;
	
	@Column(nullable = true)
	private Boolean is_nfl_free;
	
	@Column(nullable = true)
	private Boolean present_owner_nfl;
	
	@Column(nullable = true,length=500)
	private String ua_land_smaller_reason;

	@Column(nullable = true)
	private Double ua_land_area;

	@Column(nullable = true)
	private Double trees_to_be_cut;

	@Column(nullable = true)
	private Double trees_to_be_planted;

	@Column(nullable = true)
	private Double trees_compensation_ratio;
	
	@Column(nullable = true)
	private Double ca_land;
	
	@Column(nullable = true)
	private String trees_plantation_land_type;
	
	@Column(nullable = true,length = 500)
	private String reason;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "identified_land_for_compensatory_afforestaion_copy_id", nullable = true)
	private DocumentDetails identified_land_for_compensatory_afforestaion_copy;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_mou_id", nullable = true)
	private DocumentDetails copy_of_mou;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormCAfforestationDetails() {
		this.is_active=true;
		this.is_delete=false;
	}
	
	public FcFormCAfforestationDetails(Integer id) {
		this.id=id;
	}

	public FcFormCAfforestationDetails(Integer id, String comp_afforestation_type,
			Boolean is_applicable_compensatory_afforestation, Boolean is_non_forest_land, Integer total_patches,
			Integer total_districts_involved_in_ca, Boolean is_ua_land_smaller_from_proposed, Boolean is_nfl_free,
			Boolean present_owner_nfl, String ua_land_smaller_reason, Double ua_land_area, Double trees_to_be_cut,
			Double trees_to_be_planted, Double trees_compensation_ratio, Double ca_land,
			String trees_plantation_land_type,String reason) {
		super();
		this.id = id;
		this.comp_afforestation_type = comp_afforestation_type;
		this.is_applicable_compensatory_afforestation = is_applicable_compensatory_afforestation;
		this.is_non_forest_land = is_non_forest_land;
		this.total_patches = total_patches;
		this.total_districts_involved_in_ca = total_districts_involved_in_ca;
		this.is_ua_land_smaller_from_proposed = is_ua_land_smaller_from_proposed;
		this.is_nfl_free = is_nfl_free;
		this.present_owner_nfl = present_owner_nfl;
		this.ua_land_smaller_reason = ua_land_smaller_reason;
		this.ua_land_area = ua_land_area;
		this.trees_to_be_cut = trees_to_be_cut;
		this.trees_to_be_planted = trees_to_be_planted;
		this.trees_compensation_ratio = trees_compensation_ratio;
		this.ca_land = ca_land;
		this.trees_plantation_land_type = trees_plantation_land_type;
		this.reason=reason;
	}
	
	
}
