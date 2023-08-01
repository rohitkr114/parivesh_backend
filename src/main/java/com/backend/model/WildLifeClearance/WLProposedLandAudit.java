package com.backend.model.WildLifeClearance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.model.ForestClearanceProposedDiversionsAudit;
import com.backend.model.PriorApprovals;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "wl_proposed_land_audit", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WLProposedLandAudit extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true,length = 500)
	private String location_of_project_area;
	
	private Double no_of_division;
	
	private Double total_division_land;
	
	private Double total_component_land;
	
	@Column(nullable = true)
	private Double inside_protected_area;
	
	@Column(nullable = true)
	private Double outside_protected_area;
	
	@Column(nullable = true)
	private Double total_area_protected;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_id", nullable = true)
	@JsonIgnore
	private WildLifeClearanceAudit wildLifeClearanceaudit;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "animal_passage_plan_id", nullable = true)
	private DocumentDetails animal_passage_plan;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "diversion_map_copy_id", nullable = true)
	private DocumentDetails diversion_map_copy;
	
	@OneToMany(mappedBy = "wlProposedLandAudit", cascade = CascadeType.ALL)
	@Where(clause = "isDelete='false'")
	List<ForestClearanceProposedDiversionsAudit> wlProposedDiversionsAudit=new ArrayList<>();
	
	//added on 26112022
	
	@OneToMany(mappedBy = "wlProposedLandAudit", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLDivisionLandDetailAudit> wldivisionLandDetailsAudit = new ArrayList<>();	
	
	@OneToMany(mappedBy = "wlProposedLandAudit", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLComponentWiseDetailsAudit> wlComponentWiseDetailsAudit = new ArrayList<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "biodiversity_impact_assessment_id", nullable = true)
	private DocumentDetails biodiversity_impact_assessment;
	
	//added on 17-01-2023
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cost_benefit_analysis_id", nullable = true)
	private DocumentDetails cost_benefit_analysis;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "proponent_undertaken_report_id", nullable = true)
	private DocumentDetails proponent_undertaken_report;
	
	//end added on 26112022
	
	//added on 02122022
	@OneToMany(mappedBy = "wlProposedLandAudit", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLLinearProjectLandDetailsAudit> wlComponentWiseDetailsLinearAudit = new ArrayList<>();
	//end added on 02122022
	 
	//added on 03122022	
	@Column(nullable = true)
	private Double total_component_land_linear;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "plan_for_transmission_lines_id", nullable = true)
	private DocumentDetails plan_for_transmission_lines;	
	//added on 03122022	
	
	
	//added on 30112022
	@Column(name = "protected_area", nullable = true, length = 100)
	private String protected_area;
	
	@Column(name = "is_project_require_clearance_under_ec_act", columnDefinition="boolean default false", nullable = false)
	private Boolean is_project_require_clearance_under_ec_act = false;
//	end added on 30112022
	
	private Integer total_land_period;
	
	private Double distance_from_protected_area;
	
	private Integer no_of_trees_to_be_cut;
	
	//added on 05122022
	@Column(nullable = true)
	private String status_ec_clearance;
	@Column(nullable = true)
	private String status_of_application;
	@Column(nullable = true)
	private String proposalNo;
	@Column(nullable = true)
	private String issue_of_ec;
	@Column(nullable = true)
	private String moefccFileNo;
	@Column(nullable = true)
	private String application;
	@Column(nullable = true)
	private String application_reason;
	//end added on 05122022
	//added on 19122022
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_letter_id", nullable = true)
	private DocumentDetails ec_letter;
	//end added on 19122022
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;

	
}
