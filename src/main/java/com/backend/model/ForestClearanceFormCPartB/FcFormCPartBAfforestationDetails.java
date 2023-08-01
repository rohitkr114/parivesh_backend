package com.backend.model.ForestClearanceFormCPartB;

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
@Table(name = "fc_form_c_part_b_afforestation_details", schema = "master")
public class FcFormCPartBAfforestationDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormCPartB fcFormCPartB;
	
	@Column(length = 100,nullable = true)
	private String patch_wise_details;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map_copy_id", nullable = true)
	private DocumentDetails documentDetails_geo_referenced_map_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_india_copy_id", nullable = true)
	private DocumentDetails documentDetails_survey_india_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "competent_authority_certificate_id", nullable = true)
	private DocumentDetails documentDetails_competent_authority_certificate;
	
	@Column(nullable = true)
	private Double total_financial_outlay;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ca_scheme_copy_id", nullable = true)
	private DocumentDetails documentDetails_ca_scheme_copy;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(nullable = true)
	private Integer district_code;
	
	@Column(length = 100,nullable = true)
	private String district_name;
	
	@Column(nullable = true)
	private Double district_ga_area;
	
	@Column(nullable = true)
	private Double district_forest_area;
	
	@Column(nullable = true)
	private Double district_total_area;
	
	@Column(nullable = true)
	private Integer no_approved_cases;
	
	@Column(nullable = true)
	private Double forest_land_ca;
	
	@Column(length = 50,nullable = true)
	private String afforestation_progress;

	@Column(length = 10,nullable = true)
	private Integer no_of_trees;

	@Column(length = 10,nullable = true)
	private Integer no_of_trees_proposed;

	@Column(length = 255,nullable = true)
	private String compensatory_plantation_land;
	
	@Column(nullable = true)
	private Double forest_land_area;
	
	@Column(nullable = true)
	private Double non_forest_land_area;
	
	@Column(nullable = true)
	private Boolean is_applicable_compensatory_afforestation;

	@Column(nullable = true)
	private String add_ca_land;

	@Column(nullable = false)
	private Boolean is_active;

	@Column(nullable = false)
	private Boolean is_deleted;
	
	FcFormCPartBAfforestationDetails(){
		this.is_active = true;
		this.is_deleted = false;
	}
	
	
	

}
