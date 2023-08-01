package com.backend.model.ForestClearancePartB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.backend.model.DocumentDetails;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;


@Data
@Entity
@Table(name="forest_clearance_part_b_basic_details",schema = "master")
public class ForestClearanceBBasicDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_id", nullable = true)
	private ForestClearance forestClearance;
	*/
	
	@Column(nullable = false)
	private Integer fc_id;
	
//	@Column(nullable = false)
//	private Integer division_id;
	
	@Column(nullable = true)
	private Integer proposal_for;
	
	@Column(nullable = true)
	private Integer project_id;
	
	@Column(nullable=true)
	private Double felled_tree;
	
	@Column(length = 100,nullable = true)
	private String sw_no;
	
//	@Column(length = 100,nullable = false)
//	private String division_name;
//	
//	@Column(length = 100,nullable = false)
//	private Double division_area;
//	
//	@Column(length = 100,nullable = false)
//	private String district_name;
	
	@Column(length = 300,nullable = true)
	private String project_nature_description;
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
	
	@Column(length = 1000,nullable = false)
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

	@OneToOne(mappedBy = "forestClearanceBBasicDetails",cascade = CascadeType.ALL)
	private ForestClearanceBAfforestationDetails forestClearanceBAfforestationDetails;
	
	@OneToMany(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<ForestClearanceBComponentDetails> forestClearanceBComponentDetails = new ArrayList<>();
	
	@OneToMany(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<ForestClearanceBPatches> forestClearanceBPatches = new ArrayList<>();
	
	@OneToMany(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<ForestClearanceBTreeDetails> forestClearanceBTreeDetails = new ArrayList<>();
	
	@OneToMany(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<ForestClearanceBViolationDetails> forestClearanceBViolationDetails = new ArrayList<>();
	
	@OneToOne(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private ForestClearanceBWLSpecificDetails forestClearanceBWLSpecificDetails;
	
	@OneToMany(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<ForestClearanceBDensityOfVegetation> forestClearanceBDensityOfVegetation;
	
	@OneToOne(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private ForestClearanceBOtherDetails forestClearanceBOtherDetails;

	@OneToMany(targetEntity = ForestClearanceBDistrictWise.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "forestClearanceBBasicDetails_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<ForestClearanceBDistrictWise> forestClearanceBDistrictWises = new HashSet<>();

	@OneToMany(targetEntity = ForestClearanceBLegalStatus.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "forestClearanceBBasicDetails_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<ForestClearanceBLegalStatus> forestClearanceBLegalStatus = new HashSet<>();

	@OneToMany(mappedBy = "forestClearanceBBasicDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<ForestClearanceBCaLand> forestClearanceBCaLand = new ArrayList<>();
	
    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private DepartmentApplication departmentApplication;
    
    @Column(nullable = true)
	private Boolean is_tree_cutted;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	
	public ForestClearanceBBasicDetails(){
		this.is_active=true;
		this.is_deleted=false;
		this.violation_work_inprogress=false;
		this.violation_carried_out=false;
	}
}
