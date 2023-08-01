package com.backend.model.EcFormVPart2Model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "public_hearing_details", schema = "master")
public class EcFormVPart2PublicHearingdetails extends Auditable<Integer> {
	
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
	private Integer distance;

	@Column(name = "no_of_people_attended", nullable = true)
	private Integer no_of_people_attended;

	@Column(name = "designation_of_presiding", nullable = true, length = 50)
	private String designation_of_presiding;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "public_hearing_copy_id", nullable = true)
	private DocumentDetails public_hearing_copy;
	
//	@ManyToOne
//	@JoinColumn(name = "ec_form_v_part2_id", nullable = true)
//	@JsonIgnore
//	private EcFormVPart2 ecFormVPart2;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcFormVPart2PublicHearingdetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
