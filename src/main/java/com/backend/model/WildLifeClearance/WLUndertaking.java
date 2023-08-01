package com.backend.model.WildLifeClearance;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "wl_undertaking", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WLUndertaking extends Auditable<Integer>{

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
	@JoinColumn(name = "wl_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	@Column(name="undertaking_person_district", length = 255 , nullable = true)
	private String undertaking_person_district;
	@Column(name="undertaking_person_state", length = 255 , nullable = true)
	private String undertaking_person_state;
	@Column(name="undertaking_person_pin", length = 255 , nullable = true)
	private String undertaking_person_pin;
	
	@Column(name="undertaking_form_type", length = 25 , nullable = true)
	private String undertaking_form_type;

	
	
	
}
