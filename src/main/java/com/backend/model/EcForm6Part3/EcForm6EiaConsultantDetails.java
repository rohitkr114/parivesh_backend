package com.backend.model.EcForm6Part3;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_eia_consultant_details", schema = "master")
public class EcForm6EiaConsultantDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//@Column(name = "caf_id", nullable = false, unique = true, length = 50)
	//private String caf_id;

	@Column(name = "eia_consultant_engaged")
	private Boolean is_eia_consultant_engaged;  //Done

	@Column(name = "eia_consultant_name", length = 200)
	private String consultant_name; //Done

	@Column(name = "address", length = 200)
	private String consultant_address; //Done

	@Column(name = "mobile_no", length = 10)
	private String consultant_mobile; //Done

	@Column(name = "email_id", length = 50)
	private String consultant_email; //Done

	@Column(name = "accreditation_category", length = 200)
	private String consultant_category; //Done

	@Column(name = "accreditation_sector", length = 200)
	private String consultant_sectors; //Done

	@Column(name = "accreditation_validity")
	private Date validity_of_accreditation; //Done
	//private String validity_of_accreditation; //Done

	@Column(name = "reason_of_consultant_not_engagment")
	private String no_eia_consultant_engaged; //Done
	
	//This is newly added
	@Column(name = "accreditation_no_or_organisation_id")
	private String accreditation_no_or_organisation_id;
	
	@Transient
	private String lastStatus;
	
	@Column(name = "ec_form6_basic_detail_id")
	private Integer ecForm6BasicDetailId;
}