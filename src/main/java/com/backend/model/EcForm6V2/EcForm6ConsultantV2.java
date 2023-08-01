package com.backend.model.EcForm6V2;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form6_consultant", schema = "master")
public class EcForm6ConsultantV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form6_id", nullable = false)
	@JsonIgnore
	private EcForm6ProjectDetailsV2 ecForm6;
	
	@Column(name = "is_eia_consultant_engaged",nullable = true)
	private Boolean is_eia_consultant_engaged;
	
	@Column(name = "no_eia_consultant_engaged",nullable = true, length = 500)
	private String no_eia_consultant_engaged;
	
	@Column(name = "accreditation_no_or_organisation_id",nullable = true, length = 500)
	private String accreditation_no_or_organisation_id;
	
	@Column(name = "consultant_name",nullable = true, length = 500)
	private String consultant_name;
	
	@Column(name = "consultant_address",nullable = true, length = 1000)
	private String consultant_address;
	
	@Column(name = "consultant_mobile",nullable = true, length = 255)
	private String consultant_mobile;
	
	@Column(name = "consultant_email",nullable = true, length = 255)
	private String consultant_email;
	
	@Column(name = "consultant_category",nullable = true, length = 500)
	private String consultant_category;
	
	@Column(name = "consultant_sectors",nullable = true, length = 1000)
	private String consultant_sectors;
	
	@Column(name = "validity_of_accreditation",nullable = true, length = 500)
	private String validity_of_accreditation;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
}
