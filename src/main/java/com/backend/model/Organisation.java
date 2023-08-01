package com.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "Organisation", schema = "master")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Organisation extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@Column(name = "organisation_name_as_per_pan", nullable = true)
	private String organisation_name_as_per_pan;
	
	@Column(name="is_pan_company_name_same")
	private Boolean is_pan_company_name_same=true;
	
	@Column(nullable = true,length = 400)
	private String name_of_Entity_roc;

//	@Pattern(regexp = "([L|U]{1}[0-9]{5}[A-Za-z]{2}[0-9]{4}[A-Za-z]{3}[0-9]{6})|([A-Za-z]{3}-[0-9]{4})|null")
	private String cin_no;

	private Date year_of_inc;

	@Column(name = "state_ut")
	private String state_ut;

	@Column(name = "entity_type")
	private String entity_type;

	@Column(name = "district")
	private String district;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
//	@Pattern(regexp = "^[1-9]{1}[0-9]{2}[0-9]{3}$")
	private String pincode;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "address", length = 1000)
//	@NotEmpty(message = "Address can not be empty")
	private String address;

	@Column(name = "email")
//	@Email(message = "Email can not be empty")
	private String email;

	@Column(name = "mobile_no", nullable = true)
	private String mobile_no;

//	@JsonManagedReference(value = "organisation_reference")
	@OneToMany(mappedBy = "organisation")
	@JsonIgnore
	private List<Employee> employee = new ArrayList<>();

	public Organisation(ProjectProponentEntity entity, String entityType) {
		this.organisation_name_as_per_pan = entity.getOrganisation_name_as_per_pan();
		this.is_pan_company_name_same=entity.getIs_pan_company_name_same();
		this.name_of_Entity_roc=entity.getName_of_Entity_roc();
		this.name = entity.getName_of_Entity();
		this.cin_no = entity.getCin_no();
		this.year_of_inc = entity.getYear_of_inc();
		this.state_ut = entity.getState_ut();
		this.entity_type = entityType;
		this.district = entity.getDistrict();
		this.pincode = entity.getPincode();
		this.address = entity.getAddress();
		this.email = entity.getEmail();
		this.mobile_no = entity.getMobile();
	}

	public Organisation(ProjectProponentOther entity2, String type) {
		this.organisation_name_as_per_pan = entity2.getOrganisation_name_as_per_pan();
		this.name = entity2.getName_of_Entity();
		this.state_ut = entity2.getState_ut();
		this.entity_type = type;
		this.district = entity2.getDistrict();
		this.pincode = entity2.getPincode();
		this.address = entity2.getAddress();
		this.email = entity2.getEmail();
		this.mobile_no = entity2.getMobile();
	}

	public Organisation(ProjectProponentGovernment entity2, String type) {
		this.organisation_name_as_per_pan = entity2.getOrganisation_name_as_per_pan();
		this.name = entity2.getName_of_Entity();
		this.state_ut = entity2.getState_ut();
		this.entity_type = type;
		this.district = entity2.getDistrict();
		this.pincode = entity2.getPincode();
		this.address = entity2.getAddress();
		this.email = entity2.getEmail();
		this.mobile_no = entity2.getMobile();
	}

	public Organisation(ProjectProponentIndividual individual2, String type) {
		this.organisation_name_as_per_pan = individual2.getOrganisation_name_as_per_pan();
		this.name = individual2.getName_of_Entity();
		this.state_ut = individual2.getState_ut();
		this.entity_type = type;
		this.district = individual2.getDistrict();
		this.pincode = individual2.getPincode();
		this.address = individual2.getAddress();
		this.email = individual2.getEmail();
		this.mobile_no = individual2.getMobile();
	}

	public Organisation() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCin_no() {
		return cin_no;
	}

	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}

	public Date getYear_of_inc() {
		return year_of_inc;
	}

	public void setYear_of_inc(Date year_of_inc) {
		this.year_of_inc = year_of_inc;
	}

	public String getState_ut() {
		return state_ut;
	}

	public void setState_ut(String state_ut) {
		this.state_ut = state_ut;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public String getOrganisation_name_as_per_pan() {
		return organisation_name_as_per_pan;
	}

	public void setOrganisation_name_as_per_pan(String organisation_name_as_per_pan) {
		this.organisation_name_as_per_pan = organisation_name_as_per_pan;
	}

	public Boolean getIs_pan_company_name_same() {
		return is_pan_company_name_same;
	}

	public void setIs_pan_company_name_same(Boolean is_pan_company_name_same) {
		this.is_pan_company_name_same = is_pan_company_name_same;
	}

	public String getName_of_Entity_roc() {
		return name_of_Entity_roc;
	}

	public void setName_of_Entity_roc(String name_of_Entity_roc) {
		this.name_of_Entity_roc = name_of_Entity_roc;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	
}
