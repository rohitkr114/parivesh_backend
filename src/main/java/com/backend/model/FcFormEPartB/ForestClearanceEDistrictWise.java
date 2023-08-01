package com.backend.model.FcFormEPartB;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name= "fc_form_e_part_b_district_wise", schema="master")
public class ForestClearanceEDistrictWise extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100)
	private String district_name;

	@Column(length = 100, nullable = true)
	private String district_code;
	
	private Double area;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_file_id", nullable = true)
	private DocumentDetails kml_file;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fcFormEPartBBasicDetails_id", nullable = false)
	@JsonIgnore
	private ForestClearanceEBasicDetails fcFormEPartBBasicDetails;

	@OneToMany(targetEntity = FcFormEPartBDistrictWiseKmlDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_e_part_b_district_wise_id", referencedColumnName = "id",nullable = true)
	private Set<FcFormEPartBDistrictWiseKmlDetails> fcFormEPartBDistrictWiseKmlDetails = new HashSet<>();


	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public ForestClearanceEDistrictWise() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
