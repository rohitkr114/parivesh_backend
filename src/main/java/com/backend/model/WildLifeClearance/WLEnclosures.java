package com.backend.model.WildLifeClearance;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "wl_enclosures", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WLEnclosures extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "biodiversity_impact_assessment_id", nullable = true)
	private DocumentDetails biodiversity_impact_assessment;
	
	//added on 17-01-2023
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cost_benefit_analysis_id", nullable = true)
	private DocumentDetails cost_benefit_analysis;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "specific_details_xml_id", nullable = true)
	private DocumentDetails proponent_undertaken_report;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approved_mining_plan_id", nullable = true)
	private DocumentDetails sector_specific_details;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_map_outer_boundary_id", nullable = true)
	private DocumentDetails copy_of_mining_plan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_detailed_land_use_plan_id", nullable = true)
	private DocumentDetails copy_of_mining_lease;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_detail_land_id", nullable = true)
	private DocumentDetails copy_detail_land;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_perspecting_licence_id", nullable = true)
	private DocumentDetails copy_perspecting_licence;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approval_accorded_moefcc_id", nullable = true)
	private DocumentDetails approval_moefcc;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_transportation_minerals_proposed_id", nullable = true)
	private DocumentDetails transportation_minerals_proposed;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approval_competent_cat_id", nullable = true)
	private DocumentDetails copy_approval_competent_cat;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approved_cat_id", nullable = true)
	private DocumentDetails copy_approved_CAT;

	@Column(nullable = true,length = 1000)
	private String additional_information;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_additional_information_id", nullable = true)
	private DocumentDetails copy_additional_information;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;

	
}
