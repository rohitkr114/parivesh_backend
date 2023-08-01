package com.backend.model.ForestClearanceFormCPartB;

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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBEnumerationDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name= "fc_form_c_part_b_district_wise", schema="master")
public class FcFormCPartBDistrictWise extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100)
	private String district_name;

	@Column(length = 100, nullable = true)
	private String district_code;
	
	private Double area;
	
	@Column(nullable = true)
	private Integer division_id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_file_id", nullable = true)
	private DocumentDetails kml_file;

	@Column(nullable = true, length = 1000)
	private String remarks;
	/*
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_c_part_b_district_wise_id", nullable = true)
	@Where(clause = "is_deleted ='false'")
	private Set<FcFormCPartBDistrictWiseKmlDetails> fcFormCPartBDistrictKmlDetails = new HashSet<>();
	*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormCPartB fcFormCPartB;
	
	@OneToMany(targetEntity = FcFormCPartBDistrictWiseKmlDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_c_part_b_district_wise_id", referencedColumnName = "id",nullable = true)
	private Set<FcFormCPartBDistrictWiseKmlDetails> fcFormCPartBDistrictWiseKmlDetails = new HashSet<>();
	
	@Column(nullable = true)
	private String division_name;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public FcFormCPartBDistrictWise() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
