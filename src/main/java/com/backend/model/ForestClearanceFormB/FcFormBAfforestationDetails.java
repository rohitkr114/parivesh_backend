package com.backend.model.ForestClearanceFormB;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_afforestation_details", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FcFormBAfforestationDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
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

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ca_map_copy_id", nullable = true)
	private DocumentDetails ca_map_copy;

	@Column(nullable = true)
	private Boolean is_ua_land_smaller_from_proposed;
	
	@Column(nullable = true)
	private Boolean is_nfl_free;
	
	@Column(nullable = true)
	private Boolean present_owner_nfl;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_mou_id", nullable = true)
	private DocumentDetails copy_of_mou;
	
	@Column(nullable = true)
	private String ua_land_smaller_reason;

	@Column(nullable = true)
	private double ua_land_area;

	@Column(nullable = true)
	private Double no_of_trees_planted;

	@Column(nullable = true)
	private Double no_of_trees_surviving;

	@Column(nullable = true)
	private Double trees_compensation_ratio;
	
	@Column(nullable = true)
	private Double ca_land;
	
	@Column(nullable = true)
	private String trees_plantation_land_type;
	
	@Column(nullable = true)
	private String area_of_non_forest;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "identified_land_for_compensatory_afforestaion", nullable = true)
	private DocumentDetails identified_land_for_compensatory_afforestaion;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(nullable = true, length = 1000)
	private String provide_reasons_thereof;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBAfforestationDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBAfforestationDetails(Integer id) {
		this.id = id;
	}
	
	
}
