package com.backend.model.EcForm6V2;

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
@Table(name="ec_form_6_undertaking_v2",schema="master")
public class EcForm6UndertakingV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form6_id", nullable = false)
	@JsonIgnore
	private EcForm6ProjectDetailsV2 ecForm6;

	@Column(name = "is_undertaking_checked", nullable = true)
	private Boolean is_undertaking_checked;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "designation", nullable = true)
	private String designation;

	@Column(name = "company", nullable = true)
	private String company;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "esign", nullable = true)
	private String esign;

	@Column(name = "date", nullable = true)
	private Date date;

	@Transient
	private Date submission_date;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
	
	@Column(name = "identification_no", nullable = true)
	private String identification_no;
}
