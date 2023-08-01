package com.backend.model.Crz;

import java.util.Date;

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
import com.backend.model.EcPartC.EcGroundWaterLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "crz_project_details", schema = "master")
public class CrzProjectDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crz_id", nullable = false)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;

	@Column(name = "clause_of_crz_notification", nullable = true, length = 100)
	private String clause_of_crz_notification;

	@Column(name = "crz_map_indicating", nullable = true)
	private boolean crz_map_indicating;

	@Column(name = "project_layout_superimposed_on_crz", nullable = true)
	private boolean project_layout_superimposed_on_crz;

	@Column(name = "crz_map", nullable = true)
	private boolean crz_map;

	@Column(name = "crz_map_indicating_crz", nullable = true)
	private boolean crz_map_indicating_crz;

	@Column(name = "distance_of_project", nullable = true)
	private Double distance_of_project;

	@Column(name = "details_of_forest", nullable = true, length = 100)
	private String details_of_forest;

	@Column(name = "details_of_tree_cutting", nullable = true)
	private Integer details_of_tree_cutting;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "compensatory_afforestation_plan", nullable = true)
	private DocumentDetails compensatory_afforestation_plan;

	@Column(name = "distance_of_proposed_project", nullable = true, length = 1000)
	private String distance_of_proposed_project;

	@Column(name = "noc_from_state_pollution", nullable = true)
	private boolean noc_from_state_pollution;

	@Column(name = "conditions_imposed", nullable = true, length = 100)
	private String conditions_imposed;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_noc", nullable = true)
	private DocumentDetails copy_of_noc;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_crz_map_HTL_id", nullable = true)
	private DocumentDetails upload_crz_map_HTL;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_project_superimposed_crz_id", nullable = true)
	private DocumentDetails upload_project_superimposed_crz;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "crz_map_site_project_id", nullable = true)
	private DocumentDetails crz_map_site_project;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_crz_map_notified_esa_id", nullable = true)
	private DocumentDetails upload_crz_map_notified_esa;
	
	@Column(length = 30,nullable = true)
	private String agency_crz_map;
	
	@Column(nullable = true)
	private Date agency_date_of_report;
	
	@Column(name = "crz_map_indicating_reason", nullable = true, length = 500)
	private String crz_map_indicating_reason;
	
	@Column(name = "crz_map_indicating_crz_reason", nullable = true, length = 500)
	private String crz_map_indicating_crz_reason;
	
	@Column(name = "noc_from_state_pollution_reason", nullable = true, length = 500)
	private String noc_from_state_pollution_reason;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "agency_upload_report_authorised_agency_id", nullable = true)
	private DocumentDetails agency_upload_report_authorised_agency;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "details_of_tree_cutting_copy_id", nullable = true)
	private DocumentDetails details_of_tree_cutting_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "compliance_of_condition_copy_id", nullable = true)
	private DocumentDetails compliance_of_condition_copy;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public String getclause_of_crz_notification() {
//		return clause_of_crz_notification;
//	}

//	public void setclause_of_crz_notification(String clause_of_crz_notification) {
//		this.clause_of_crz_notification = clause_of_crz_notification;
//	}

//	public boolean iscrz_map_indicating() {
//		return crz_map_indicating;
//	}

//	public void setcrz_map_indicating(boolean crz_map_indicating) {
//		this.crz_map_indicating = crz_map_indicating;
//	}

//	public boolean isproject_layout_superimposed_on_crz() {
//		return project_layout_superimposed_on_crz;
//	}

//	public void setproject_layout_superimposed_on_crz(boolean project_layout_superimposed_on_crz) {
//		this.project_layout_superimposed_on_crz = project_layout_superimposed_on_crz;
//	}

//	public boolean iscrz_map() {
//		return crz_map;
//	}

//	public void setcrz_map(boolean crz_map) {
//		this.crz_map = crz_map;
//	}

//	public boolean iscrz_map_indicating_crz() {
//		return crz_map_indicating_crz;
//	}

//	public void setcrz_map_indicating_crz(boolean crz_map_indicating_crz) {
//		this.crz_map_indicating_crz = crz_map_indicating_crz;
//	}

//	public Double getdistance_of_project() {
//		return distance_of_project;
//	}

//	public void setdistance_of_project(Double distance_of_project) {
//		this.distance_of_project = distance_of_project;
//	}

//	public String getdetails_of_forest() {
//		return details_of_forest;
//	}

//	public void setdetails_of_forest(String details_of_forest) {
//		this.details_of_forest = details_of_forest;
//	}

//	public Integer getdetails_of_tree_cutting() {
//		return details_of_tree_cutting;
//	}

//	public void setdetails_of_tree_cutting(Integer details_of_tree_cutting) {
//		this.details_of_tree_cutting = details_of_tree_cutting;
//	}

//	public String getdistance_of_proposed_project() {
//		return distance_of_proposed_project;
//	}

//	public void setdistance_of_proposed_project(String distance_of_proposed_project) {
//		this.distance_of_proposed_project = distance_of_proposed_project;
//	}

//	public boolean isnoc_from_state_pollution() {
//		return noc_from_state_pollution;
//	}

//	public void setnoc_from_State_Pollution(boolean noc_from_state_pollution) {
//		this.noc_from_state_pollution = noc_from_state_pollution;
//	}

//	public String getconditions_imposed() {
//		return conditions_imposed;
//	}

//	public void setconditions_imposed(String conditions_imposed) {
//		this.conditions_imposed = conditions_imposed;
//	}

	public CrzBasicDetails getCrzBasicDetails() {
		return crzBasicDetails;
	}

	public void setCrzBasicDetails(CrzBasicDetails crzBasicDetails) {
		this.crzBasicDetails = crzBasicDetails;
	}

	public String getClause_of_crz_notification() {
		return clause_of_crz_notification;
	}

	public void setClause_of_crz_notification(String clause_of_crz_notification) {
		this.clause_of_crz_notification = clause_of_crz_notification;
	}

	public boolean isCrz_map_indicating() {
		return crz_map_indicating;
	}

	public void setCrz_map_indicating(boolean crz_map_indicating) {
		this.crz_map_indicating = crz_map_indicating;
	}

	public boolean isProject_layout_superimposed_on_crz() {
		return project_layout_superimposed_on_crz;
	}

	public void setProject_layout_superimposed_on_crz(boolean project_layout_superimposed_on_crz) {
		this.project_layout_superimposed_on_crz = project_layout_superimposed_on_crz;
	}

	public boolean isCrz_map() {
		return crz_map;
	}

	public void setCrz_map(boolean crz_map) {
		this.crz_map = crz_map;
	}

	public boolean isCrz_map_indicating_crz() {
		return crz_map_indicating_crz;
	}

	public void setCrz_map_indicating_crz(boolean crz_map_indicating_crz) {
		this.crz_map_indicating_crz = crz_map_indicating_crz;
	}

	public Double getDistance_of_project() {
		return distance_of_project;
	}

	public void setDistance_of_project(Double distance_of_project) {
		this.distance_of_project = distance_of_project;
	}

	public String getDetails_of_forest() {
		return details_of_forest;
	}

	public void setDetails_of_forest(String details_of_forest) {
		this.details_of_forest = details_of_forest;
	}

	public Integer getDetails_of_tree_cutting() {
		return details_of_tree_cutting;
	}

	public void setDetails_of_tree_cutting(Integer details_of_tree_cutting) {
		this.details_of_tree_cutting = details_of_tree_cutting;
	}

	public DocumentDetails getCompensatory_afforestation_plan() {
		return compensatory_afforestation_plan;
	}

	public void setCompensatory_afforestation_plan(DocumentDetails compensatory_afforestation_plan) {
		this.compensatory_afforestation_plan = compensatory_afforestation_plan;
	}

	public String getDistance_of_proposed_project() {
		return distance_of_proposed_project;
	}

	public void setDistance_of_proposed_project(String distance_of_proposed_project) {
		this.distance_of_proposed_project = distance_of_proposed_project;
	}

	public boolean isNoc_from_state_pollution() {
		return noc_from_state_pollution;
	}

	public void setNoc_from_state_pollution(boolean noc_from_state_pollution) {
		this.noc_from_state_pollution = noc_from_state_pollution;
	}

	public String getConditions_imposed() {
		return conditions_imposed;
	}

	public void setConditions_imposed(String conditions_imposed) {
		this.conditions_imposed = conditions_imposed;
	}

	public DocumentDetails getCopy_of_noc() {
		return copy_of_noc;
	}

	public void setCopy_of_noc(DocumentDetails copy_of_noc) {
		this.copy_of_noc = copy_of_noc;
	}

	public DocumentDetails getUpload_crz_map_HTL() {
		return upload_crz_map_HTL;
	}

	public void setUpload_crz_map_HTL(DocumentDetails upload_crz_map_HTL) {
		this.upload_crz_map_HTL = upload_crz_map_HTL;
	}

	public DocumentDetails getUpload_project_superimposed_crz() {
		return upload_project_superimposed_crz;
	}

	public void setUpload_project_superimposed_crz(DocumentDetails upload_project_superimposed_crz) {
		this.upload_project_superimposed_crz = upload_project_superimposed_crz;
	}

	public DocumentDetails getCrz_map_site_project() {
		return crz_map_site_project;
	}

	public void setCrz_map_site_project(DocumentDetails crz_map_site_project) {
		this.crz_map_site_project = crz_map_site_project;
	}

	public DocumentDetails getUpload_crz_map_notified_esa() {
		return upload_crz_map_notified_esa;
	}

	public void setUpload_crz_map_notified_esa(DocumentDetails upload_crz_map_notified_esa) {
		this.upload_crz_map_notified_esa = upload_crz_map_notified_esa;
	}

	public String getAgency_crz_map() {
		return agency_crz_map;
	}

	public void setAgency_crz_map(String agency_crz_map) {
		this.agency_crz_map = agency_crz_map;
	}

	public Date getAgency_date_of_report() {
		return agency_date_of_report;
	}

	public void setAgency_date_of_report(Date agency_date_of_report) {
		this.agency_date_of_report = agency_date_of_report;
	}

	public DocumentDetails getAgency_upload_report_authorised_agency() {
		return agency_upload_report_authorised_agency;
	}

	public void setAgency_upload_report_authorised_agency(DocumentDetails agency_upload_report_authorised_agency) {
		this.agency_upload_report_authorised_agency = agency_upload_report_authorised_agency;
	}

	public DocumentDetails getDetails_of_tree_cutting_copy() {
		return details_of_tree_cutting_copy;
	}

	public void setDetails_of_tree_cutting_copy(DocumentDetails details_of_tree_cutting_copy) {
		this.details_of_tree_cutting_copy = details_of_tree_cutting_copy;
	}

	public DocumentDetails getCompliance_of_condition_copy() {
		return compliance_of_condition_copy;
	}

	public void setCompliance_of_condition_copy(DocumentDetails compliance_of_condition_copy) {
		this.compliance_of_condition_copy = compliance_of_condition_copy;
	}
	
	
	
}
