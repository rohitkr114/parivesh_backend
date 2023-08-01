package com.backend.model.FcFormBPartB;

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
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartB;
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartBDistrictWiseKmlDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_part_b_district_wise", schema = "master")
public class FcFormBPartBDistrictWise extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100)
	private String district_name;

	@Column(length = 100, nullable = true)
	private String district_code;
	
	@Column(nullable = true)
	private String division_name;

	private Double area;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fcFormBPartBBasicDetails_id", nullable = false)
	@JsonIgnore
	private FcFormBPartBBasicDetails fcFormBPartBBasicDetails;

	@OneToMany(targetEntity = FcFormBPartBDistrictWiseKmlDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_c_part_b_district_wise_id", referencedColumnName = "id",nullable = true)
	private Set<FcFormBPartBDistrictWiseKmlDetails> fcFormBPartBDistrictWiseKmlDetails = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_file_id", nullable = true)
	private DocumentDetails kml_file;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBPartBDistrictWise() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
