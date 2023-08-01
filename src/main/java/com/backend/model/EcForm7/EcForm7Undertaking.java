package com.backend.model.EcForm7;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form7_undertaking", schema = "master")
public class EcForm7Undertaking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form_7_id", nullable = true)
	@JsonIgnore
	private EcForm7 ecForm7;

	@Column(name = "i_agree", nullable = true)
	private boolean i_agree;

	@Column(name = "undertaking_person_name", length = 100, nullable = true)
	private String undertaking_person_name;

	@Column(name = "undertaking_person_designation", length = 100, nullable = true)
	private String undertaking_person_designation;

	@Column(name = "undertaking_person_company", length = 255, nullable = true)
	private String undertaking_person_company;

	@Column(name = "undertaking_person_address", length = 255, nullable = true)
	private String undertaking_person_address;

	@Column(name = "undertaking_person_esign", length = 255, nullable = true)
	private String undertaking_person_esign;

	@Column(name = "undertaking_date", nullable = true)
	private Date undertaking_date;

	@Transient
	private Date submission_date;
	
	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_delete;
	
	@Column(name = "identification_no", nullable = true)
	private String identification_no;

	public EcForm7Undertaking() {
		this.is_active = true;
		this.is_delete = false;
	}

	public EcForm7Undertaking(Integer id, boolean i_agree, String undertaking_person_name,
			String undertaking_person_designation, String undertaking_person_company, String undertaking_person_address,
			String undertaking_person_esign, Date undertaking_date) {
		this.id = id;
		this.i_agree = i_agree;
		this.undertaking_person_name = undertaking_person_name;
		this.undertaking_person_designation = undertaking_person_designation;
		this.undertaking_person_company = undertaking_person_company;
		this.undertaking_person_address = undertaking_person_address;
		this.undertaking_person_esign = undertaking_person_esign;
		this.undertaking_date = undertaking_date;
	}

	public EcForm7Undertaking(Integer id) {
		this.id = id;
	}
	
}
