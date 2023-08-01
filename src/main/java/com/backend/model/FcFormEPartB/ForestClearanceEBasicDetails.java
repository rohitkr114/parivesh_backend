package com.backend.model.FcFormEPartB;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "fc_form_e_part_b_basic_details", schema = "master")
public class ForestClearanceEBasicDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer fc_id;

    @Column(nullable = true)
    private Integer proposal_for;

    @Column(nullable = true)
    private Double felled_tree;

    @Column(nullable = true)
    private Integer project_id;

    @Column(length = 100, nullable = true)
    private String sw_no;

    @Column(length = 300, nullable = true)
    private String project_nature_description;

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

    @OneToOne(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private ForestClearanceEAfforestationDetails forestClearanceEAfforestationDetails;

    @OneToMany(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<ForestClearanceEComponentDetails> forestClearanceEComponentDetails = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<ForestClearanceEPatches> forestClearanceEPatches = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<ForestClearanceETreeDetails> forestClearanceETreeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<ForestClearanceEViolationDetails> forestClearanceEViolationDetails = new ArrayList<>();

    @OneToOne(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private ForestClearanceEWLSpecificDetails forestClearanceEWLSpecificDetails;

    @OneToMany(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<ForestClearanceEDensityOfVegetation> forestClearanceEDensityOfVegetation;

    @OneToOne(mappedBy = "forestClearanceEBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private ForestClearanceEOtherDetails forestClearanceEOtherDetails;
	
	/*
	@OneToMany(targetEntity = ForestClearanceEDistrictWise.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "forestClearanceEBasicDetails_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<ForestClearanceEDistrictWise> forestClearanceEDistrictWises = new HashSet<>();
	*/

    @OneToMany(mappedBy = "fcFormEPartBBasicDetails")
    @Where(clause = "is_deleted ='false'")
    private List<ForestClearanceEDistrictWise> forestClearanceEDistrictWises = new ArrayList<>();

    @OneToMany(targetEntity = ForestClearanceELegalStatus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "forestClearanceEBasicDetails_id", referencedColumnName = "id")
    @Where(clause = "is_deleted ='false'")
    private Set<ForestClearanceELegalStatus> forestClearanceELegalStatus = new HashSet<>();

    @OneToMany(mappedBy = "fcFormEPartBBasicDetails", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<FcFormEPartBCaLand> fcFormEPartBCaLands = new ArrayList<>();

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private DepartmentApplication departmentApplication;

    private Boolean is_active;

    private Boolean is_deleted;

    @Column(nullable = true)
    private Boolean is_tree_cutted;

    public ForestClearanceEBasicDetails() {
        this.is_active = true;
        this.is_deleted = false;
        this.violation_work_inprogress = false;
        this.violation_carried_out = false;
    }
}
