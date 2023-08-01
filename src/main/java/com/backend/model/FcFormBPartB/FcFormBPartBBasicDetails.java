package com.backend.model.FcFormBPartB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.model.ForestClearancePartB.*;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartBDistrictWise;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_part_b_basic_details", schema = "master")
public class FcFormBPartBBasicDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer fc_id;

//	@Column(nullable = false)
//	private Integer division_id;

	@Column(nullable = true)
	private Integer proposal_for;

	@Column(nullable = true)
	private Integer project_id;

	@Column(length = 100, nullable = true)
	private String sw_no;

//	@Column(length = 100,nullable = false)
//	private String division_name;
//	
//	@Column(length = 100,nullable = false)
//	private Double division_area;
//	
//	@Column(length = 100,nullable = false)
//	private String district_name;

	@Column(length = 300, nullable = true)
	private String project_nature_description;
	
	@Column(nullable=true)
	private Double felled_tree;
//	
//	@Column(nullable = false)
//	private Double forest_land;
//	
//	@Column(length = 100,nullable = false)
//	private String forest_land_legal_status;

//	@Column(nullable = false)
//	private Double vegetation_area;
//	
//	@Column(nullable = false)
//	private Double vegetation_canopy_density;
//	
//	@Column(length = 100,nullable = false)
//	private String vegetation_eco_class;

	@Column(length = 1000, nullable = false)
	private String erosion_description;

	@Column(nullable = true)
	private Double approx_diversion_distance;

	@Column(nullable = true)
	private Boolean violation_work_inprogress;

	@Column(nullable = true, length = 1000)
	private String working_plan_prescription;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "working_plan_prescription_copy_id", nullable = true)
	private DocumentDetails working_plan_prescription_copy;

	@Column(nullable = false)
	private Boolean violation_carried_out;
//	
//	@Column(length = 100,nullable = true)
//	private String please_specify_legal;

	@OneToOne(mappedBy = "fcFormBPartBBasicDetails")
	private FcFormBPartBAfforestationDetails fcFormBPartBAfforestationDetails;

	@OneToMany(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBComponentDetails> fcFormBPartBComponentDetails = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBPatches> fcFormBPartBPatches = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBTreeDetails> fcFormBPartBTreeDetails = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBViolationDetails> fcFormBPartBViolationDetails = new ArrayList<>();

	@OneToOne(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBPartBWLSpecificDetails fcFormBPartBWLSpecificDetails;

	@OneToMany(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBDensityOfVegetation> fcFormBPartBDensityOfVegetation;

	@OneToOne(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBPartBOtherDetails fcFormBPartBOtherDetails;

	/*
	@OneToMany(targetEntity = FcFormBPartBDistrictWise.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fcFormBPartBBasicDetails_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<FcFormBPartBDistrictWise> fcFormBPartBDistrictWises = new HashSet<>();
	*/
	
	@OneToMany(mappedBy = "fcFormBPartBBasicDetails")
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBDistrictWise> fcFormBPartBDistrictWises = new ArrayList<>();
	

	@OneToMany(targetEntity = FcFormBPartBLegalStatus.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fcFormBPartBBasicDetails_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<FcFormBPartBLegalStatus> fcFormBPartBLegalStatus = new HashSet<>();

	@OneToMany(mappedBy = "fcFormBPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPartBCaLand> fcFormBPartBCaLands = new ArrayList<>();

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	@Column(nullable = true)
	private Boolean is_tree_cutted;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBPartBBasicDetails() {
		this.is_active = true;
		this.is_deleted = false;
		this.violation_work_inprogress = false;
		this.violation_carried_out = false;
	}
}
