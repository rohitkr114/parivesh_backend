package com.backend.model.FcFormDPartB;

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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data	
@Entity
@Table(name = "fc_form_d_part_b_basic_details", schema = "master")
public class FcFormDPartBBasicDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private Integer fc_id;	

	@Column(nullable = true)
	private Integer proposal_for;
	
	@Column(nullable=true)
	private Double felled_tree;

	@Column(nullable = true)
	private Integer project_id;

	@Column(length = 100, nullable = true)
	private String sw_no;
	
	//Section 1
	@OneToMany(mappedBy = "fcFormDPartBBasicDetails")
	@Where(clause = "is_deleted ='false'")
	private List<FcFormDPartBDistrictWise> fcFormDPartBDistrictWises = new ArrayList<>();
	
	//Section 2
	@OneToMany(targetEntity = FcFormDPartBLegalStatus.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fcFormDPartBBasicDetails_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<FcFormDPartBLegalStatus> fcFormDPartBLegalStatus = new HashSet<>();	

	//Section 3
	@OneToMany(mappedBy = "fcFormDPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormDPartBDensityOfVegetation> fcFormDPartBDensityOfVegetation;
	
	//Section 4
//	@OneToMany(targetEntity = FcFormDPartBEnumerationDetails.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fcFormDPartBBasicDetails_id", referencedColumnName = "id")
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormDPartBEnumerationDetails> fcFormDPartBEnumerationDetails = new HashSet<>();
//	
	@OneToMany(mappedBy = "fcFormDPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormDPartBTreeDetails> fcFormDPartBTreeDetails = new ArrayList<>();

	
	//Section 5
	@Column(nullable = true, length = 1000)
	private String working_plan_prescription;
	//Section 5.1
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "working_plan_prescription_copy_id", nullable = true)
	private DocumentDetails working_plan_prescription_copy;
	
	//Section 6
	@Column(length = 1000, nullable = false)
	private String erosion_description;
	
	//Section 7
	@Column(nullable = true)
	private Double approx_diversion_distance;
	
	//Section 8
	@OneToOne(mappedBy = "fcFormDPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormDPartBWLSpecificDetails fcFormDPartBWLSpecificDetails;
	
	//Section 9
	@Column(nullable = false)
	private Boolean violation_carried_out;
	
	@OneToMany(mappedBy = "fcFormDPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormDPartBViolationDetails> fcFormDPartBViolationDetails = new ArrayList<>();
	
	//Section 9.1.7
	@Column(nullable = true)
	private Boolean violation_work_inprogress;
	
	//Section 10 & 11
	@OneToOne(mappedBy = "fcFormDPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormDPartBAfforestationDetails fcFormDPartBAfforestationDetails;

	//Additional information api already exist
	
	//Recommended and site Inspection
	@OneToOne(mappedBy = "fcFormDPartBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormDPartBOtherDetails fcFormDPartBOtherDetails;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	@Column(nullable = true)
	private Boolean is_tree_cutted;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormDPartBBasicDetails() {
		this.is_active = true;
		this.is_deleted = false;
		this.violation_work_inprogress = false;
	}	
}
