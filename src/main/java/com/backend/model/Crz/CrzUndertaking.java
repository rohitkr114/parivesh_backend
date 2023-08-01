package com.backend.model.Crz;

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
@Table(name = "crz_undertaking", schema = "master")
public class CrzUndertaking extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crz_id", nullable = false)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;

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
}
