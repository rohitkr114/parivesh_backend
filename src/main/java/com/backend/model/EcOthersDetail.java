package com.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_others_details", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EcOthersDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "i_agree", nullable = true)
	private boolean i_agree;

	@Column(name = "undertaking_person_name", length = 100, nullable = false)
	private String undertaking_person_name;

	@Column(name = "undertaking_person_designation", length = 100, nullable = false)
	private String undertaking_person_designation;

	@Column(name = "undertaking_person_company", length = 255, nullable = false)
	private String undertaking_person_company;

	@Column(name = "undertaking_person_address", length = 255, nullable = false)
	private String undertaking_person_address;

	@Column(name = "undertaking_person_esign", length = 255, nullable = true)
	private String undertaking_person_esign;

	@Column(name = "undertaking_date", nullable = true)
	private Date undertaking_date;
	
	@Transient
	private Date submission_date;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EnvironmentClearence enviromentClearence;
	
	@Column(name = "identification_no", nullable = true)
	private String identification_no;

	public EcOthersDetail() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isI_agree() {
		return i_agree;
	}

	public void setI_agree(boolean i_agree) {
		this.i_agree = i_agree;
	}

	public String getUndertaking_person_name() {
		return undertaking_person_name;
	}

	public void setUndertaking_person_name(String undertaking_person_name) {
		this.undertaking_person_name = undertaking_person_name;
	}

	public String getUndertaking_person_designation() {
		return undertaking_person_designation;
	}

	public void setUndertaking_person_designation(String undertaking_person_designation) {
		this.undertaking_person_designation = undertaking_person_designation;
	}

	public String getUndertaking_person_company() {
		return undertaking_person_company;
	}

	public void setUndertaking_person_company(String undertaking_person_company) {
		this.undertaking_person_company = undertaking_person_company;
	}

	public String getUndertaking_person_address() {
		return undertaking_person_address;
	}

	public void setUndertaking_person_address(String undertaking_person_address) {
		this.undertaking_person_address = undertaking_person_address;
	}

	public String getUndertaking_person_esign() {
		return undertaking_person_esign;
	}

	public void setUndertaking_person_esign(String undertaking_person_esign) {
		this.undertaking_person_esign = undertaking_person_esign;
	}

	public Date getUndertaking_date() {
		return undertaking_date;
	}

	public void setUndertaking_date(Date undertaking_date) {
		this.undertaking_date = undertaking_date;
	}

	public EnvironmentClearence getEnviromentClearence() {
		return enviromentClearence;
	}

	public void setEnviromentClearence(EnvironmentClearence enviromentClearence) {
		this.enviromentClearence = enviromentClearence;
	}

}
