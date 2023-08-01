package com.backend.model.FcFormAPartBDc;

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
@Table(name = "fc_form_a_part_b_dc_underatking", schema = "master")
public class FcFormAPartBDCUndertaking extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_formA_DC_id", nullable = true)
	@JsonIgnore
	private FcFormAPartBDC fcFormAPartBDC;

	private Boolean is_active;

	private Boolean is_delete;

	public FcFormAPartBDCUndertaking() {
		this.is_active = true;
		this.is_delete = false;
	}

	public FcFormAPartBDCUndertaking(Integer id, boolean i_agree, String undertaking_person_name,
			String undertaking_person_designation, String undertaking_person_company, String undertaking_person_address,
			String undertaking_person_esign, Date undertaking_date, Date submission_date) {
		this.id = id;
		this.i_agree = i_agree;
		this.undertaking_person_name = undertaking_person_name;
		this.undertaking_person_designation = undertaking_person_designation;
		this.undertaking_person_company = undertaking_person_company;
		this.undertaking_person_address = undertaking_person_address;
		this.undertaking_person_esign = undertaking_person_esign;
		this.undertaking_date = undertaking_date;
		this.submission_date = submission_date;

	}

}
