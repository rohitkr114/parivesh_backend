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
import com.backend.model.PriorApprovals;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@Entity
@Table(name = "wl_proposed_land", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WLProposedLand extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// added on 21-03-2023
	@Column(nullable = true)
	private String location_of_project_area_others; 
	
	// added on 13-03-2023
	@Column(nullable = true)
	private Double core_zone_area; 
	@Column(nullable = true)
	private Double buffer_zone_area; 	
	
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
	private WildLifeClearance wildLifeClearance;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "animal_passage_plan_id", nullable = true)
	private DocumentDetails animal_passage_plan;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "diversion_map_copy_id", nullable = true)
	private DocumentDetails diversion_map_copy;
	
	@OneToMany(mappedBy = "wlProposedLand", cascade = CascadeType.ALL)
	@Where(clause = "isDelete='false'")
	List<ForestClearanceProposedDiversions> wlProposedDiversions=new ArrayList<>();
	
	//added on 26112022
	
	@OneToMany(mappedBy = "wlProposedLand", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLDivisionLandDetail> wldivisionLandDetails = new ArrayList<>();	
	
	@OneToMany(mappedBy = "wlProposedLand", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLComponentWiseDetails> wlComponentWiseDetails = new ArrayList<>();

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
	@OneToMany(mappedBy = "wlProposedLand", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLLinearProjectLandDetails> wlComponentWiseDetailsLinear = new ArrayList<>();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation_of_project_area() {
		return location_of_project_area;
	}

	public void setLocation_of_project_area(String location_of_project_area) {
		this.location_of_project_area = location_of_project_area;
	}

	public Double getNo_of_division() {
		return no_of_division;
	}

	public void setNo_of_division(Double no_of_division) {
		this.no_of_division = no_of_division;
	}

	public Double getTotal_division_land() {
		return total_division_land;
	}

	public void setTotal_division_land(Double total_division_land) {
		this.total_division_land = total_division_land;
	}

	public Double getTotal_component_land() {
		return total_component_land;
	}

	public void setTotal_component_land(Double total_component_land) {
		this.total_component_land = total_component_land;
	}

	public Double getInside_protected_area() {
		return inside_protected_area;
	}

	public void setInside_protected_area(Double inside_protected_area) {
		this.inside_protected_area = inside_protected_area;
	}

	public Double getOutside_protected_area() {
		return outside_protected_area;
	}

	public void setOutside_protected_area(Double outside_protected_area) {
		this.outside_protected_area = outside_protected_area;
	}

	public Double getTotal_area_protected() {
		return total_area_protected;
	}

	public void setTotal_area_protected(Double total_area_protected) {
		this.total_area_protected = total_area_protected;
	}

	public WildLifeClearance getWildLifeClearance() {
		return wildLifeClearance;
	}

	public void setWildLifeClearance(WildLifeClearance wildLifeClearance) {
		this.wildLifeClearance = wildLifeClearance;
	}

	public DocumentDetails getAnimal_passage_plan() {
		return animal_passage_plan;
	}

	public void setAnimal_passage_plan(DocumentDetails animal_passage_plan) {
		this.animal_passage_plan = animal_passage_plan;
	}

	public DocumentDetails getDiversion_map_copy() {
		return diversion_map_copy;
	}

	public void setDiversion_map_copy(DocumentDetails diversion_map_copy) {
		this.diversion_map_copy = diversion_map_copy;
	}

	public List<ForestClearanceProposedDiversions> getWlProposedDiversions() {
		return wlProposedDiversions;
	}

	public void setWlProposedDiversions(List<ForestClearanceProposedDiversions> wlProposedDiversions) {
		this.wlProposedDiversions = wlProposedDiversions;
	}

	public List<WLDivisionLandDetail> getWldivisionLandDetails() {
		return wldivisionLandDetails;
	}

	public void setWldivisionLandDetails(List<WLDivisionLandDetail> wldivisionLandDetails) {
		this.wldivisionLandDetails = wldivisionLandDetails;
	}

	public List<WLComponentWiseDetails> getWlComponentWiseDetails() {
		return wlComponentWiseDetails;
	}

	public void setWlComponentWiseDetails(List<WLComponentWiseDetails> wlComponentWiseDetails) {
		this.wlComponentWiseDetails = wlComponentWiseDetails;
	}

	public DocumentDetails getBiodiversity_impact_assessment() {
		return biodiversity_impact_assessment;
	}

	public void setBiodiversity_impact_assessment(DocumentDetails biodiversity_impact_assessment) {
		this.biodiversity_impact_assessment = biodiversity_impact_assessment;
	}

	public DocumentDetails getCost_benefit_analysis() {
		return cost_benefit_analysis;
	}

	public void setCost_benefit_analysis(DocumentDetails cost_benefit_analysis) {
		this.cost_benefit_analysis = cost_benefit_analysis;
	}

	public DocumentDetails getProponent_undertaken_report() {
		return proponent_undertaken_report;
	}

	public void setProponent_undertaken_report(DocumentDetails proponent_undertaken_report) {
		this.proponent_undertaken_report = proponent_undertaken_report;
	}

	public List<WLLinearProjectLandDetails> getWlComponentWiseDetailsLinear() {
		return wlComponentWiseDetailsLinear;
	}

	public void setWlComponentWiseDetailsLinear(List<WLLinearProjectLandDetails> wlComponentWiseDetailsLinear) {
		this.wlComponentWiseDetailsLinear = wlComponentWiseDetailsLinear;
	}

	public Double getTotal_component_land_linear() {
		return total_component_land_linear;
	}

	public void setTotal_component_land_linear(Double total_component_land_linear) {
		this.total_component_land_linear = total_component_land_linear;
	}

	public DocumentDetails getPlan_for_transmission_lines() {
		return plan_for_transmission_lines;
	}

	public void setPlan_for_transmission_lines(DocumentDetails plan_for_transmission_lines) {
		this.plan_for_transmission_lines = plan_for_transmission_lines;
	}

	public String getProtected_area() {
		return protected_area;
	}

	public void setProtected_area(String protected_area) {
		this.protected_area = protected_area;
	}

	public Boolean getIs_project_require_clearance_under_ec_act() {
		return is_project_require_clearance_under_ec_act;
	}

	public void setIs_project_require_clearance_under_ec_act(Boolean is_project_require_clearance_under_ec_act) {
		this.is_project_require_clearance_under_ec_act = is_project_require_clearance_under_ec_act;
	}

	public Integer getTotal_land_period() {
		return total_land_period;
	}

	public void setTotal_land_period(Integer total_land_period) {
		this.total_land_period = total_land_period;
	}

	public Double getDistance_from_protected_area() {
		return distance_from_protected_area;
	}

	public void setDistance_from_protected_area(Double distance_from_protected_area) {
		this.distance_from_protected_area = distance_from_protected_area;
	}

	public Integer getNo_of_trees_to_be_cut() {
		return no_of_trees_to_be_cut;
	}

	public void setNo_of_trees_to_be_cut(Integer no_of_trees_to_be_cut) {
		this.no_of_trees_to_be_cut = no_of_trees_to_be_cut;
	}

	public String getStatus_ec_clearance() {
		return status_ec_clearance;
	}

	public void setStatus_ec_clearance(String status_ec_clearance) {
		this.status_ec_clearance = status_ec_clearance;
	}

	public String getStatus_of_application() {
		return status_of_application;
	}

	public void setStatus_of_application(String status_of_application) {
		this.status_of_application = status_of_application;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getIssue_of_ec() {
		return issue_of_ec;
	}

	public void setIssue_of_ec(String issue_of_ec) {
		this.issue_of_ec = issue_of_ec;
	}

	public String getMoefccFileNo() {
		return moefccFileNo;
	}

	public void setMoefccFileNo(String moefccFileNo) {
		this.moefccFileNo = moefccFileNo;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getApplication_reason() {
		return application_reason;
	}

	public void setApplication_reason(String application_reason) {
		this.application_reason = application_reason;
	}

	public DocumentDetails getEc_letter() {
		return ec_letter;
	}

	public void setEc_letter(DocumentDetails ec_letter) {
		this.ec_letter = ec_letter;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	
	public Double getCore_zone_area() {
		return core_zone_area;
	}

	public void setCore_zone_area(Double core_zone_area) {
		this.core_zone_area = core_zone_area;
	}

	public Double getBuffer_zone_area() {
		return buffer_zone_area;
	}

	public void setBuffer_zone_area(Double buffer_zone_area) {
		this.buffer_zone_area = buffer_zone_area;
	}
	
	public String getLocation_of_project_area_others() {
		return location_of_project_area_others;
	}

	public void setLocation_of_project_area_others(String location_of_project_area_others) {
		this.location_of_project_area_others = location_of_project_area_others;
	}

	public WLProposedLand() {
		this.is_active = true;
        this.is_delete = false;
	}
	
}
