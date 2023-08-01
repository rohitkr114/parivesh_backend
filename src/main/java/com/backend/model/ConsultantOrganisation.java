package com.backend.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.dto.ConsultOrganisation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "consultant_organisation", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ConsultantOrganisation extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "org_id")
	private String org_id;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "head", nullable = true)
	private String head;

	@Column(name = "landline_no", nullable = true)
	private String landline_no;

	@Column(name = "mobile_no", nullable = true)
	private String mobile_no;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "designation", nullable = true)
	private String designation;

	@Column(name = "accreditation_no", nullable = true)
	private String Accreditation_No;

	@Column(name = "address", length = 1000, nullable = true)
	private String address;

	@Column(name = "accreditation_validity", nullable = true)
	private Date accreditation_validity;

	@Column(name = "category", length = 10, nullable = true)
	private String category;

	@Column(name = "validity_flag", length = 50)
	private String validity_flag;

	@Column(name = "accreditation_sectors", length = 500, nullable = true)
	private String accreditation_sectors;

	@Column(name = "pan", nullable = true)
	private String pan;

	@Column(name = "state_ut", nullable = true)
	private String state_ut;

	@Column(name = "district", nullable = true)
	private String district;

	@Column(name = "pincode", nullable = true)
	private String pincode;

	@Column(name = "website", nullable = true)
	private String website;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	/*
	 * @OneToMany(mappedBy = "consultantOrganisation", cascade = CascadeType.ALL)
	 * private List<Consultant> consultant = new ArrayList<>();
	 */

	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JsonIgnore
	 * 
	 * @JoinColumn(nullable = false, name = "consultant_id", referencedColumnName =
	 * "id") private Consultant consultant;
	 */

	@ManyToMany(mappedBy = "consultantOrganisations")
	private Set<Consultant> consultants = new HashSet<>();

	public ConsultantOrganisation() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public ConsultantOrganisation(String org_id) {
		this.org_id = org_id;
	}

	public ConsultantOrganisation(ConsultOrganisation consultantOrganisation) {
		this.org_id = consultantOrganisation.getOrgId();
		this.name = consultantOrganisation.getOrganizationName();
		this.Accreditation_No = consultantOrganisation.getAccreditationNo();
		this.address = consultantOrganisation.getAddress();
		try {
			if (consultantOrganisation.getValidityOfAccreditation().equals("NA")) {
				this.accreditation_validity = new Date();
			} else if (consultantOrganisation.getValidityOfAccreditation().contains("AM")) {
				this.accreditation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultantOrganisation.getValidityOfAccreditation().split("\\s+")[0]).getTime());
			} else {
				this.accreditation_validity = new Date(new SimpleDateFormat("MM/d/yyyy")
						.parse(consultantOrganisation.getValidityOfAccreditation()).getTime());
				//this.accreditation_validity = new Date(new SimpleDateFormat("dd/mm/yyyy").parse(consultantOrganisation.getValidityOfAccreditation()).getTime());
				//this.accreditation_validity = new Date(
						//new SimpleDateFormat("dd/mm/yyyy").parse(consultantOrganisation.getValidityOfAccreditation())); yyyy-MM-dd
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.category = consultantOrganisation.getCategory();
		this.validity_flag = consultantOrganisation.getValididityFlag();
		this.accreditation_sectors = consultantOrganisation.getSectorsOfAccreditation();
		this.pan = consultantOrganisation.getPanNo();
		this.email = consultantOrganisation.getEmailId();
		this.head = consultantOrganisation.getOrganizationHead();
		this.landline_no = consultantOrganisation.getLandlineNo();
		this.mobile_no = consultantOrganisation.getMobileNo();
		this.designation = consultantOrganisation.getDesignation();
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccreditation_No() {
		return Accreditation_No;
	}

	public void setAccreditation_No(String accreditation_No) {
		Accreditation_No = accreditation_No;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAccreditation_validity() {
		return accreditation_validity;
	}

	public void setAccreditation_validity(Date accreditation_validity) {
		this.accreditation_validity = accreditation_validity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValidity_flag() {
		return validity_flag;
	}

	public void setValidity_flag(String validity_flag) {
		this.validity_flag = validity_flag;
	}

	public String getAccreditation_sectors() {
		return accreditation_sectors;
	}

	public void setAccreditation_sectors(String accreditation_sectors) {
		this.accreditation_sectors = accreditation_sectors;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	/*
	 * public List<Consultant> getConsultant() { return consultant; }
	 * 
	 * 
	 * public void setConsultant(List<Consultant> consultant) { this.consultant =
	 * consultant; }
	 */

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getLandline_no() {
		return landline_no;
	}

	public void setLandline_no(String landline_no) {
		this.landline_no = landline_no;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getState_ut() {
		return state_ut;
	}

	public void setState_ut(String state_ut) {
		this.state_ut = state_ut;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<Consultant> getConsultants() {
		return consultants;
	}

	public void setConsultants(Set<Consultant> consultants) {
		this.consultants = consultants;
	}

}
