package com.backend.model.EcForm6Part5;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "ec_form6_undertaking", schema = "master")
public class EcForm6Undertaking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form6_impl_basic_detail_id", nullable = true)
	@JsonIgnore
	private EcForm6BasicDetails ecForm6BasicDetails;

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

	//@Temporal(TemporalType.DATE)
	@Column(name = "undertaking_date", nullable = true)
	//private Date undertaking_date;
	private String undertaking_date;

	@Transient
	private Date submission_date;
	
	@Transient
	private String lastStatus;
	
	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_delete;

	public EcForm6Undertaking() {
		this.is_active = true;
		this.is_delete = false;
	}


	public EcForm6Undertaking(Integer id) {
		this.id = id;
	}
	
}
