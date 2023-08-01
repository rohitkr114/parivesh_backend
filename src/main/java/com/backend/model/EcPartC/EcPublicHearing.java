package com.backend.model.EcPartC;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_public_hearing", schema = "master")
public class EcPublicHearing extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "date_of_advertisment", nullable = true)
	private Date date_of_advertisment;

	@Column(name = "date_of_PH", nullable = true)
	private Date date_of_PH;

	@Column(name = "state", nullable = true)
	private String state;
	
	@Column(name = "state_name", nullable = true)
	private String state_name;
	
	@Column(name = "district", nullable = true)
	private String district;
	
	@Column(name = "district_name", nullable = true)
	private String district_name;

	@Column(name = "sub_district", nullable = true)
	private String sub_district;
	
	@Column(name = "sub_district_name", nullable = true)
	private String sub_district_name;
	
	@Column(name = "village", nullable = true)
	private String village;
	
	@Column(name = "village_name", nullable = true)
	private String village_name;
	
	@Column(name = "distance", nullable = true)
	private String distance;

	@Column(name = "no_of_people_attended", nullable = true)
	private Integer no_of_people_attended;

	@Column(name = "designation_of_presiding", nullable = true, length = 50)
	private String designation_of_presiding;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "public_hearing_copy_id", nullable = true)
	private DocumentDetails public_hearing_copy;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;

	public EcPublicHearing() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
