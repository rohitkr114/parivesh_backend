package com.backend.model.EcPartC;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ec_partc_undertaking", schema = "master")
public class EcUndertaking extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EcPartC ecPartC;

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

	public EcUndertaking(Integer id, Boolean is_undertaking_checked, String name, String designation, String company,
			String address, String esign, Date date) {
		this.id = id;
		this.is_undertaking_checked = is_undertaking_checked;
		this.name = name;
		this.designation = designation;
		this.company = company;
		this.address = address;
		this.esign = esign;
		this.date = date;
	}

}
