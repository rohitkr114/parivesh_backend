package com.backend.model.FcFormEPartB;

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
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartB;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
@Table(name = "fc_form_e_part_b_afforestation_details", schema = "master")
public class ForestClearanceEAfforestationDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Boolean is_ca_land_applicable;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_e_part_b_id", nullable = false)
	@JsonIgnore
	private ForestClearanceEBasicDetails forestClearanceEBasicDetails;
	
	@Column(length = 100)
	private String patch_wise_details;

	@Column(nullable = true)
	private Boolean is_project_affected_trees;
	
	@Column(nullable = true)
	private String component;
	
	@Column(nullable = true)
	private Double layout_plan_of_proposal_a;
	
	@Column(nullable = true)
	private Double layout_plan_of_proposal_b;
	
	@Column(nullable = true)
	private Double net_change;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map_copy_id", nullable = true)
	private DocumentDetails documentDetails_geo_referenced_map_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_india_copy_id", nullable = true)
	private DocumentDetails documentDetails_survey_india_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "competent_authority_certificate_id", nullable = true)
	private DocumentDetails documentDetails_competent_authority_certificate;
	
	private Double total_financial_outlay;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ca_scheme_copy_id", nullable = true)
	private DocumentDetails documentDetails_ca_scheme_copy;
	
	private Integer district_code;
	
	@Column(length = 100)
	private String district_name;
	
	private Double district_ga_area;
	
	private Double district_forest_area;
	
	private Double district_total_area;
	
	private Integer no_approved_cases;
	
	private Double forest_land_ca;
	
	@Column(length = 50)
	private String afforestation_progress;

	@Column(length = 10)
	private Integer no_of_trees;

	@Column(length = 10)
	private Integer no_of_trees_proposed;

	@Column(length = 255)
	private String compensatory_plantation_land;
	
	private Double forest_land_area;
	
	private Double non_forest_land_area;

	@Column(nullable = true)
	private String add_ca_land;


	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;
	
	ForestClearanceEAfforestationDetails(){
		this.is_active = true;
		this.is_deleted = false;
	}
	
	public ForestClearanceEAfforestationDetails(Integer id){
		this.id=id;
	}
	

}
