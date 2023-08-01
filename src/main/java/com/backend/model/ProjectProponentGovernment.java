package com.backend.model;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ProjectProponentGovernment", schema = "master")
public class ProjectProponentGovernment extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(name = "name_of_Entity")
//	@NotEmpty(message = "Name can not be empty")
	private String name_of_Entity;

	@Column(name = "organisation_name_as_per_pan", nullable = true)
	private String organisation_name_as_per_pan;
	
	@Column(name="address")
//	@NotEmpty(message = "Address can not be empty")
	private String address;

	@Column(name = "email", unique = true)
//	@Email(message = "Email can not be empty")
	private String email;

	@Column(name = "mobile")
//	@Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$")
	private String mobile;

	@Column(name = "state_ut", nullable = true)
//	@NotEmpty(message = "state can not be empty")
	private String state_ut;

	@Column(name = "district", nullable = true)
//	@NotEmpty(message = "District can not be empty")
	private String district;

	@Column(name = "pincode", nullable = true)
//	@Pattern(regexp = "^[1-9]{1}[0-9]{2}[0-9]{3}$")
	private String pincode;

	@JsonIgnore
	@Column(name = "is_active")
	private boolean is_active;

	@JsonIgnore
	@Column(name = "is_delete")
	private boolean is_delete;

	@Column(name = "security_question")
	private String security_question;

	@Column(name = "security_answer")
	private String security_answer;
	private String name_of_Contact_Person;

	@Column(nullable = true)
	private String landline_No;
	
	@Column(nullable = true,length = 10)
	private String std_code;

	// @Pattern(regexp =
	// "([L|U]{1}[0-9]{5}[A-Za-z]{2}[0-9]{4}[A-Za-z]{3}[0-9]{6})|([A-Za-z]{3}-[0-9]{4})|null")
	private String cin_no;

	private Date year_of_inc;

	private String state;

	private String pan_no;

	private String website;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "authority_letter_id", nullable = true)
	private DocumentDetails authority_letter;

	public ProjectProponentGovernment() {
		this.is_active = true;
		this.is_delete = false;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName_of_Entity() {
		return name_of_Entity;
	}

	public void setName_of_Entity(String name_of_Entity) {
		this.name_of_Entity = name_of_Entity;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getSecurity_question() {
		return security_question;
	}

	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}

	public String getSecurity_answer() {
		return security_answer;
	}

	public void setSecurity_answer(String security_answer) {
		this.security_answer = security_answer;
	}

	public String getName_of_Contact_Person() {
		return name_of_Contact_Person;
	}

	public void setName_of_Contact_Person(String name_of_Contact_Person) {
		this.name_of_Contact_Person = name_of_Contact_Person;
	}

	public String getLandline_No() {
		return landline_No;
	}

	public void setLandline_No(String landline_No) {
		this.landline_No = landline_No;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
