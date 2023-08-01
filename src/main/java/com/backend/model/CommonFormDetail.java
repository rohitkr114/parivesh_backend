package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "caf_details", schema = "master")
//@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
public class CommonFormDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "project_id", referencedColumnName = "id")
//	@JsonBackReference(value = "project_reference")
	@JsonIgnore
	private ProjectDetails projectDetails;
	
	@Column(name = "project_name", length = 1000, nullable = true)
	private String project_name;
	
	@Column(name = "sw_no", length = 100,nullable = true)
	private String project_sw_no;
	
	@Column(name = "is_proposal_expansion", length = 50, nullable = true)
	private String isProposalExpansion;

	@OneToMany(mappedBy = "commonFormDetail", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted=false")
	private List<CafKML> cafKML = new ArrayList<>();

	@Column(name = "project_id", nullable = false, updatable = false, insertable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer project_id;

	@Column(name = "caf_id", unique = true, length = 50, nullable = false)
	private String caf_id;

	@Column(name = "caf_id_sequence")
	@JsonIgnore
	private Integer caf_id_sequence;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "caf_status", nullable = false)
	private AppConstant.Caf_Status caf_status;
	
	@Column(name = "prior_proposal_id", length = 100, nullable = true)
	private String prior_proposal_id;

	@Column(nullable = true)
	private Integer parent_id;
	
	@Column(nullable = true)
	private String amendment_form;
	
	public CommonFormDetail(Integer id, ProjectDetails projectDetails, String project_name, String project_sw_no,
			List<CafKML> cafKML, Integer project_id, String caf_id,String seven_to_a, Integer caf_id_sequence, Caf_Status caf_status,
			String prior_proposal_id, String project_description, String proposal_for, String proposal_for_old, String organization_name,
			String organization_name_roc,Boolean is_pan_company_name_same,String organization_street, String organization_city, Integer organization_district,
			Integer organization_state, State organisationState, District organisationDistrict,
			String organization_pincode, String organization_landmark, String organization_email,
			String organization_landline_no, String organization_mobile_no, String organization_legal_status,
			String applicant_name, String applicant_designation, String applicant_street, String applicant_city,
			Integer applicant_district, Integer applicant_state, State applicantState, District applicantDistrict,
			String applicant_pincode, String applicant_landmark, String applicant_email, String applicant_landline_no,
			String applicant_mobile_no, boolean applicant_i_agree, CafLocationOfKml cafLocationOfKml,
			CafProjectActivityCost cafProjectActivityCost, CafOthers cafOthers, String amendment_form) {
		super();
		this.id = id;
		this.projectDetails = projectDetails;
		this.project_name = project_name;
		this.project_sw_no = project_sw_no;
		this.cafKML = cafKML;
		this.project_id = project_id;
		this.caf_id = caf_id;
		this.seven_to_a = seven_to_a;
		this.caf_id_sequence = caf_id_sequence;
		this.caf_status = caf_status;
		this.prior_proposal_id = prior_proposal_id;
		this.project_description = project_description;
		this.proposal_for = proposal_for;
		this.proposal_for_old = proposal_for_old;
		this.organization_name = organization_name;
		this.organization_name_roc=organization_name_roc;
		this.is_pan_company_name_same=is_pan_company_name_same;
		this.organization_street = organization_street;
		this.organization_city = organization_city;
		this.organization_district = organization_district;
		this.organization_state = organization_state;
		this.organisationState = organisationState;
		this.organisationDistrict = organisationDistrict;
		this.organization_pincode = organization_pincode;
		this.organization_landmark = organization_landmark;
		this.organization_email = organization_email;
		this.organization_landline_no = organization_landline_no;
		this.organization_mobile_no = organization_mobile_no;
		this.organization_legal_status = organization_legal_status;
		this.applicant_name = applicant_name;
		this.applicant_designation = applicant_designation;
		this.applicant_street = applicant_street;
		this.applicant_city = applicant_city;
		this.applicant_district = applicant_district;
		this.applicant_state = applicant_state;
		this.applicantState = applicantState;
		this.applicantDistrict = applicantDistrict;
		this.applicant_pincode = applicant_pincode;
		this.applicant_landmark = applicant_landmark;
		this.applicant_email = applicant_email;
		this.applicant_landline_no = applicant_landline_no;
		this.applicant_mobile_no = applicant_mobile_no;
		this.applicant_i_agree = applicant_i_agree;
		this.cafLocationOfKml = cafLocationOfKml;
		this.cafProjectActivityCost = cafProjectActivityCost;
		this.cafOthers = cafOthers;
		this.amendment_form = amendment_form;
	}

	public CommonFormDetail(Integer id, Integer proj_id ,Integer main_state) {
		this.id = id;
		this.project_id = proj_id;
		this.projectDetails = new ProjectDetails(proj_id,main_state);
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	@Column(name="project_description",nullable=true,length=1000)
	private String project_description;
	
	
	@Column(name = "proposal_for", nullable = false)
	private String proposal_for;

	@Column(name = "seven_to_a", nullable = true)
	private String seven_to_a;

	@Column(name = "proposal_for_old", nullable = true)
	private String proposal_for_old;

	@Column(name = "organization_name", nullable = true)
	private String organization_name;
	
	@Column(name="is_pan_company_name_same")
	private Boolean is_pan_company_name_same=true;

	@Column(name = "organization_name_roc", nullable = true)
	private String organization_name_roc;

	@Column(name = "organization_street", length = 1000, nullable = true)
	private String organization_street;

	@Column(name = "organization_city", nullable = true)
	private String organization_city;

	@Column(name = "organization_district", length = 30, nullable = true)
	private Integer organization_district;

	@Column(name = "organization_state", length = 30, nullable = true)
	private Integer organization_state;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private State organisationState;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private District organisationDistrict;

	@Column(name = "organization_pincode", length = 6, nullable = true)
	private String organization_pincode;

	@Column(name = "organization_landmark", length = 50, nullable = true)
	private String organization_landmark;

	@Column(name = "organization_email", nullable = true)
	private String organization_email;

	@Column(name = "organization_landline_no", length = 13, nullable = true)
	private String organization_landline_no;

	@Column(name = "organization_mobile_no", length = 10, nullable = true)
	private String organization_mobile_no;

	@Column(name = "organization_legal_status", length = 100, nullable = true)
	private String organization_legal_status;

	@Column(name = "applicant_name", nullable = true)
	private String applicant_name;

	@Column(name = "applicant_designation", length = 50, nullable = true)
	private String applicant_designation;

	@Column(name = "applicant_street", nullable = true)
	private String applicant_street;

	@Column(name = "applicant_city", length = 50, nullable = true)
	private String applicant_city;

	@Column(name = "applicant_district", nullable = true)
	private Integer applicant_district;

	@Column(name = "applicant_state", nullable = true)
	private Integer applicant_state;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private State applicantState;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private District applicantDistrict;

	@Column(name = "applicant_pincode", length = 6, nullable = true)
	private String applicant_pincode;

	@Column(name = "applicant_landmark", length = 50, nullable = true)
	private String applicant_landmark;

	@Column(name = "applicant_email", nullable = true)
	private String applicant_email;

	@Column(name = "applicant_landline_no", length = 13, nullable = true)
	private String applicant_landline_no;

	@Column(name = "applicant_mobile_no", length = 10, nullable = true)
	private String applicant_mobile_no;

	@Column(name = "applicant_i_agree", nullable = true)
	private boolean applicant_i_agree;

	@OneToOne(mappedBy = "commonFormDetail")
//	@JsonManagedReference(value = "caf_detail_reference")
	private CafLocationOfKml cafLocationOfKml;

	@OneToOne(mappedBy = "commonFormDetail")
//	@JsonManagedReference(value = "caf_detail_reference")
	private CafProjectActivityCost cafProjectActivityCost;

	@OneToOne(mappedBy = "commonFormDetail")
//	@JsonManagedReference(value = "caf_detail_reference")
	private CafOthers cafOthers;

	@Column(name = "is_legacy_caf", nullable = true)
	private Boolean is_legacy_caf = false;

	public CommonFormDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	public List<CafKML> getCafKML() {
		return cafKML;
	}

	public void setCafKML(List<CafKML> cafKML) {
		this.cafKML = cafKML;
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public String getCaf_id() {
		return caf_id;
	}

	public void setCaf_id(String caf_id) {
		this.caf_id = caf_id;
	}

	public Integer getCaf_id_sequence() {
		return caf_id_sequence;
	}

//	public void setProject_description(String project_description) {
//		this.project_description = project_description;
//	}
//	public String getProject_description() {
//		return project_description;
//	}

	public void setCaf_id_sequence(Integer caf_id_sequence) {
		this.caf_id_sequence = caf_id_sequence;
	}

	public AppConstant.Caf_Status getCaf_status() {
		return caf_status;
	}

	public void setCaf_status(AppConstant.Caf_Status caf_status) {
		this.caf_status = caf_status;
	}

	public String getProposal_for() {
		return proposal_for;
	}

	public void setProposal_for(String proposal_for) {
		this.proposal_for = proposal_for;
	}

	public String getProposal_for_old() {
		return proposal_for_old;
	}

	public void setProposal_for_old(String proposal_for_old) {
		this.proposal_for_old = proposal_for_old;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getOrganization_street() {
		return organization_street;
	}

	public void setOrganization_street(String organization_street) {
		this.organization_street = organization_street;
	}

	public String getOrganization_city() {
		return organization_city;
	}

	public void setOrganization_city(String organization_city) {
		this.organization_city = organization_city;
	}

	public Integer getOrganization_district() {
		return organization_district;
	}

	public String getSeven_to_a() {
		return seven_to_a;
	}

	public void setSeven_to_a(String seven_to_a) {
		this.seven_to_a = seven_to_a;
	}

	public void setOrganization_district(Integer organization_district) {
		this.organization_district = organization_district;
	}

	public Integer getOrganization_state() {
		return organization_state;
	}

	public void setOrganization_state(Integer organization_state) {
		this.organization_state = organization_state;
	}

	public State getOrganisationState() {
		return organisationState;
	}

	public void setOrganisationState(State organisationState) {
		this.organisationState = organisationState;
	}

	public District getOrganisationDistrict() {
		return organisationDistrict;
	}

	public void setOrganisationDistrict(District organisationDistrict) {
		this.organisationDistrict = organisationDistrict;
	}

	public String getOrganization_pincode() {
		return organization_pincode;
	}

	public void setOrganization_pincode(String organization_pincode) {
		this.organization_pincode = organization_pincode;
	}

	public String getOrganization_landmark() {
		return organization_landmark;
	}

	public void setOrganization_landmark(String organization_landmark) {
		this.organization_landmark = organization_landmark;
	}

	public String getOrganization_email() {
		return organization_email;
	}

	public void setOrganization_email(String organization_email) {
		this.organization_email = organization_email;
	}

	public String getOrganization_landline_no() {
		return organization_landline_no;
	}

	public void setOrganization_landline_no(String organization_landline_no) {
		this.organization_landline_no = organization_landline_no;
	}

	public String getOrganization_mobile_no() {
		return organization_mobile_no;
	}

	public void setOrganization_mobile_no(String organization_mobile_no) {
		this.organization_mobile_no = organization_mobile_no;
	}

	public String getOrganization_legal_status() {
		return organization_legal_status;
	}

	public void setOrganization_legal_status(String organization_legal_status) {
		this.organization_legal_status = organization_legal_status;
	}

	public String getApplicant_name() {
		return applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}

	public String getApplicant_designation() {
		return applicant_designation;
	}

	public void setApplicant_designation(String applicant_designation) {
		this.applicant_designation = applicant_designation;
	}

	public String getApplicant_street() {
		return applicant_street;
	}

	public void setApplicant_street(String applicant_street) {
		this.applicant_street = applicant_street;
	}

	public String getApplicant_city() {
		return applicant_city;
	}

	public void setApplicant_city(String applicant_city) {
		this.applicant_city = applicant_city;
	}

	public Integer getApplicant_district() {
		return applicant_district;
	}

	public void setApplicant_district(Integer applicant_district) {
		this.applicant_district = applicant_district;
	}

	public Integer getApplicant_state() {
		return applicant_state;
	}

	public void setApplicant_state(Integer applicant_state) {
		this.applicant_state = applicant_state;
	}

	public State getApplicantState() {
		return applicantState;
	}

	public void setApplicantState(State applicantState) {
		this.applicantState = applicantState;
	}

	public District getApplicantDistrict() {
		return applicantDistrict;
	}

	public void setApplicantDistrict(District applicantDistrict) {
		this.applicantDistrict = applicantDistrict;
	}

	public String getApplicant_pincode() {
		return applicant_pincode;
	}

	public void setApplicant_pincode(String applicant_pincode) {
		this.applicant_pincode = applicant_pincode;
	}

	public String getApplicant_landmark() {
		return applicant_landmark;
	}

	public void setApplicant_landmark(String applicant_landmark) {
		this.applicant_landmark = applicant_landmark;
	}

	public String getApplicant_email() {
		return applicant_email;
	}

	public void setApplicant_email(String applicant_email) {
		this.applicant_email = applicant_email;
	}

	public String getApplicant_landline_no() {
		return applicant_landline_no;
	}

	public void setApplicant_landline_no(String applicant_landline_no) {
		this.applicant_landline_no = applicant_landline_no;
	}

	public String getApplicant_mobile_no() {
		return applicant_mobile_no;
	}

	public void setApplicant_mobile_no(String applicant_mobile_no) {
		this.applicant_mobile_no = applicant_mobile_no;
	}

	public boolean isApplicant_i_agree() {
		return applicant_i_agree;
	}

	public void setApplicant_i_agree(boolean applicant_i_agree) {
		this.applicant_i_agree = applicant_i_agree;
	}

	public CafLocationOfKml getCafLocationOfKml() {
		return cafLocationOfKml;
	}

	public void setCafLocationOfKml(CafLocationOfKml cafLocationOfKml) {
		this.cafLocationOfKml = cafLocationOfKml;
	}

	public CafProjectActivityCost getCafProjectActivityCost() {
		return cafProjectActivityCost;
	}

	public void setCafProjectActivityCost(CafProjectActivityCost cafProjectActivityCost) {
		this.cafProjectActivityCost = cafProjectActivityCost;
	}

	public CafOthers getCafOthers() {
		return cafOthers;
	}

	public void setCafOthers(CafOthers cafOthers) {
		this.cafOthers = cafOthers;
	}
	
	

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_sw_no() {
		return project_sw_no;
	}

	public void setProject_sw_no(String project_sw_no) {
		this.project_sw_no = project_sw_no;
	}
	

	public String getPrior_proposal_id() {
		return prior_proposal_id;
	}

	public void setPrior_proposal_id(String prior_proposal_id) {
		this.prior_proposal_id = prior_proposal_id;
	}

	public String getIsProposalExpansion() {
		return isProposalExpansion;
	}

	public void setIsProposalExpansion(String isProposalExpansion) {
		this.isProposalExpansion = isProposalExpansion;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Boolean getIs_legacy_caf() {
		return is_legacy_caf;
	}

	public void setIs_legacy_caf(Boolean is_legacy_caf) {
		this.is_legacy_caf = is_legacy_caf;
	}

	public String getAmendment_form() {
		return amendment_form;
	}

	public void setAmendment_form(String amendment_form) {
		this.amendment_form = amendment_form;
	}

	public String getOrganization_name_roc() {
		return organization_name_roc;
	}

	public void setOrganization_name_roc(String organization_name_roc) {
		this.organization_name_roc = organization_name_roc;
	}

	public Boolean getIs_pan_company_name_same() {
		return is_pan_company_name_same;
	}

	public void setIs_pan_company_name_same(Boolean is_pan_company_name_same) {
		this.is_pan_company_name_same = is_pan_company_name_same;
	}
	
	
}
