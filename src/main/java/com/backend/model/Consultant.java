package com.backend.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import com.backend.dto.ConsultIndividual;
import com.backend.dto.ConsultOrganisation;
import com.backend.dto.ProjectDetailDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "consultant", schema = "master")
@PrimaryKeyJoinColumn(referencedColumnName = "entityid")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "entityid")
public class Consultant extends User {

	@Column(name = "individual_id", nullable = true)
	private Long ConsultantId;

	@Column(name = "role", length = 255, nullable = true)
	private String role;

	@Column(name = "engagement", length = 100, nullable = true)
	private String engagement;

	@Column(name = "category", length = 10, nullable = true)
	private String category;

	@Column(name = "accrediation_sectors", length = 500, nullable = true)
	private String accrediation_sectors;

	@Column(name = "accrediation_validity", nullable = true)
	private Date accrediation_validity;

	@Column(name = "validity_flag", length = 50, nullable = true)
	private String validity_flag;

	@Column(name = "website", length = 100, nullable = true)
	private String website;

	@Column(name = "is_owner")
	private Boolean is_owner;

	@Column(name = "pan", nullable = true)
	private String pan;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private List<String> orgIds;
	
	/*
	 * @Column(name = "consultant_organisation_id", nullable = false, updatable =
	 * false, insertable = false) private Integer consultant_organisation_id;
	 */

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "consultant_organisation_id", nullable = false)
	 * 
	 * @JsonIgnore private ConsultantOrganisation consultantOrganisation;
	 */

	/*
	 * @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(nullable = false) private List<ConsultantOrganisation>
	 * consultantOrganisations = new ArrayList<>();
	 */

	@ManyToMany
	@JoinTable(name = "Consultant_to_Organisation", joinColumns = {
			@JoinColumn(name = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "organisation_id") }, schema = "master")
	@JsonIgnore
	private Set<ConsultantOrganisation> consultantOrganisations = new HashSet<>();

	@ManyToMany(mappedBy = "consultants")
	@JsonIgnore
	private Set<ProjectDetails> projectDetails = new HashSet<>();
	
	@Transient
	private Set<ProjectDetailDto> projectDetailDto=new HashSet<>();

	public Consultant(ConsultOrganisation consultOrganisation, String type, HttpServletRequest request) {
		super(0, consultOrganisation.getOrganizationHead(), consultOrganisation.getAddress(),
				consultOrganisation.getEmailId(), consultOrganisation.getMobileNo(), null, null, null, type, request);
		this.role = consultOrganisation.getDesignation();
		this.category = consultOrganisation.getCategory();
		this.accrediation_sectors = consultOrganisation.getSectorsOfAccreditation();
		try {
			if (consultOrganisation.getValidityOfAccreditation().equals("NA")) {
				this.accrediation_validity = new Date();
			} else if (consultOrganisation.getValidityOfAccreditation().contains("AM")) {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultOrganisation.getValidityOfAccreditation().split("\\s+")[0]).getTime());
			} else {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultOrganisation.getValidityOfAccreditation()).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.validity_flag = consultOrganisation.getValididityFlag();
		this.pan = consultOrganisation.getPanNo();
		this.is_owner = true;
	}
	
	public Consultant(ConsultOrganisation consultOrganisation, String type) {
		super(0, consultOrganisation.getOrganizationHead(), consultOrganisation.getAddress(),
				consultOrganisation.getEmailId(), consultOrganisation.getMobileNo(), null, null, null, type);
		this.role = consultOrganisation.getDesignation();
		this.category = consultOrganisation.getCategory();
		this.accrediation_sectors = consultOrganisation.getSectorsOfAccreditation();
		try {
			if (consultOrganisation.getValidityOfAccreditation().equals("NA")) {
				this.accrediation_validity = new Date();
			} else if (consultOrganisation.getValidityOfAccreditation().contains("AM")) {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultOrganisation.getValidityOfAccreditation().split("\\s+")[0]).getTime());
			} else {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultOrganisation.getValidityOfAccreditation()).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.validity_flag = consultOrganisation.getValididityFlag();
		this.pan = consultOrganisation.getPanNo();
		this.is_owner = true;
	}

	public Consultant(ConsultIndividual consultIndividual, String type, HttpServletRequest request) {
		super(0, consultIndividual.getNameOfConsultant(), consultIndividual.getAddress(),
				consultIndividual.getEmailId(), consultIndividual.getMobileNo(), null, null, null, type, request);
		this.ConsultantId = consultIndividual.getIndividualId();
		this.role = consultIndividual.getRoleOfIndividual();
		this.category = consultIndividual.getCategory();
		this.engagement = consultIndividual.getEngagement();
		this.accrediation_sectors = consultIndividual.getSectorsOfAccreditation();
		try {
			if (consultIndividual.getValidityOfAccreditation().equals("NA")) {
				this.accrediation_validity = new Date();
			} else if (consultIndividual.getValidityOfAccreditation().contains("AM")) {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultIndividual.getValidityOfAccreditation().split("\\s+")[0]).getTime());
			} else {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultIndividual.getValidityOfAccreditation()).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.validity_flag = consultIndividual.getValididityFlag();
		this.pan = consultIndividual.getPanNo();
		this.is_owner = false;
		this.setIs_active(false);
		this.setIs_delete(true);
	}
	
	public Consultant(ConsultIndividual consultIndividual, String type) {
		super(0, consultIndividual.getNameOfConsultant(), consultIndividual.getAddress(),
				consultIndividual.getEmailId(), consultIndividual.getMobileNo(), null, null, null, type);
		this.ConsultantId = consultIndividual.getIndividualId();
		this.role = consultIndividual.getRoleOfIndividual();
		this.category = consultIndividual.getCategory();
		this.engagement = consultIndividual.getEngagement();
		this.accrediation_sectors = consultIndividual.getSectorsOfAccreditation();
		try {
			if (consultIndividual.getValidityOfAccreditation().equals("NA")) {
				this.accrediation_validity = new Date();
			} else if (consultIndividual.getValidityOfAccreditation().contains("AM")) {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultIndividual.getValidityOfAccreditation().split("\\s+")[0]).getTime());
			} else {
				this.accrediation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultIndividual.getValidityOfAccreditation()).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.validity_flag = consultIndividual.getValididityFlag();
		this.pan = consultIndividual.getPanNo();
		this.is_owner = false;
		this.setIs_active(false);
		this.setIs_delete(true);
	}

	public void addConsultantOrganisation(ConsultantOrganisation organisation) {
		this.consultantOrganisations.add(organisation);
		organisation.getConsultants().add(this);
	}

	public Long getConsultantId() {
		return ConsultantId;
	}

	public void setConsultantId(Long consultantId) {
		ConsultantId = consultantId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEngagement() {
		return engagement;
	}

	public void setEngagement(String engagement) {
		this.engagement = engagement;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAccrediation_sectors() {
		return accrediation_sectors;
	}

	public void setAccrediation_sectors(String accrediation_sectors) {
		this.accrediation_sectors = accrediation_sectors;
	}

	public Date getAccrediation_validity() {
		return accrediation_validity;
	}

	public void setAccrediation_validity(Date accrediation_validity) {
		this.accrediation_validity = accrediation_validity;
	}

	public String getValidity_flag() {
		return validity_flag;
	}

	public void setValidity_flag(String validity_flag) {
		this.validity_flag = validity_flag;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	/*
	 * public ConsultantOrganisation getConsultantOrganisation() { return
	 * consultantOrganisation; }
	 * 
	 * public void setConsultantOrganisation(ConsultantOrganisation
	 * consultantOrganisation) { this.consultantOrganisation =
	 * consultantOrganisation; }
	 */

	public Boolean getIs_owner() {
		return is_owner;
	}

	public void setIs_owner(Boolean is_owner) {
		this.is_owner = is_owner;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	/*
	 * public Integer getConsultant_organisation_id() { return
	 * consultant_organisation_id; }
	 * 
	 * public void setConsultant_organisation_id(Integer consultant_organisation_id)
	 * { this.consultant_organisation_id = consultant_organisation_id; }
	 */

	public Set<ProjectDetails> getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(Set<ProjectDetails> projectDetails) {
		this.projectDetails = projectDetails;
	}

	public Set<ConsultantOrganisation> getConsultantOrganisations() {
		return consultantOrganisations;
	}

	public void setConsultantOrganisations(Set<ConsultantOrganisation> consultantOrganisations) {
		this.consultantOrganisations = consultantOrganisations;
	}

	public List<String> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}

	
	@Override
	public String toString() {
		return "Consultant [ConsultantId=" + ConsultantId + ", role=" + role + ", engagement=" + engagement
				+ ", category=" + category + ", accrediation_sectors=" + accrediation_sectors
				+ ", accrediation_validity=" + accrediation_validity + ", validity_flag=" + validity_flag + ", website="
				+ website + ", is_owner=" + is_owner + ", pan=" + pan + "]";
	}

	public Set<ProjectDetailDto> getProjectDetailDto() {
		return projectDetailDto;
	}

	public void setProjectDetailDto(Set<ProjectDetailDto> projectDetailDto) {
		this.projectDetailDto = projectDetailDto;
	}

	
}
