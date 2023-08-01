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

import org.apache.xpath.operations.Bool;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_part_b_afforestation_details", schema = "master")
public class FcFormBPartBAfforestationDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_b_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormBPartBBasicDetails fcFormBPartBBasicDetails;

	@Column(length = 100)
	private String patch_wise_details;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map_copy_id", nullable = true)
	private DocumentDetails documentDetails_geo_referenced_map_copy;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(nullable = true)
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

	private Boolean is_applicable_compensatory_afforestation;

	private Boolean is_suitability_of_area_identified;

	private Integer expenditure_details;

	private Double expenditure_area;

	private Double total_expenditure_area;

	@Column(nullable = true)
	private Double survival_of_plantation;

	@Column(nullable = true)
	private String ca_category;

	@Column(nullable = true)
	private String conditions_stipulated;

	private String detail_of_conditions_stipulated;

	private String action_of_conditions_stipulated;

	private Double biologically;

	private Double technically;

	private Double gap;

	private Double area_handed;

	private Double area_to_be_handed;

	@Column(nullable = true)
	private String add_ca_land;
	

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	FcFormBPartBAfforestationDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
