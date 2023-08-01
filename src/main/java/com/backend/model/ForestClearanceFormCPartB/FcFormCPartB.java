package com.backend.model.ForestClearanceFormCPartB;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormD.FcFormDLegalStatus;
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
@Table(name = "fc_form_c_part_b", schema = "master")
public class FcFormCPartB extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer fc_id;

    @Column(nullable = true)
    private Integer proposal_for;

    @Column(nullable = true)
    private Integer project_id;

    @Column(nullable = true)
    private Double felled_tree;

    @Column(length = 100, nullable = true)
    private String sw_no;

    @Column(length = 300, nullable = true)
    private String project_nature_description;

    @Column(length = 1000, nullable = true)
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

    @Column(nullable = true)
    private Boolean violation_carried_out;

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private DepartmentApplication departmentApplication;

    private Boolean is_active;

    private Boolean is_deleted;

    @OneToOne(mappedBy = "fcFormCPartB")
    private FcFormCPartBAfforestationDetails fcFormCPartBAfforestationDetails;

    @OneToOne(mappedBy = "fcFormCPartB")
    private FcFormCPartBOtherDetails fcFormCPartBOtherDetails;

    @OneToOne(mappedBy = "fcFormCPartB")
    private FcFormCPartBWLSpecificDetails fcFormCPartBWLSpecificDetails;

    @OneToMany(mappedBy = "fcFormCPartB")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBComponentDetails> fcFormCPartBComponentDetails = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormCPartB")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBPatches> fcFormCPartBPatches = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormCPartB")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBTreeDetails> fcFormCPartBTreeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormCPartB")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBViolationDetails> fcFormCPartBViolationDetails = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormCPartB")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBDensityOfVegetation> fcFormCPartBDensityOfVegetations;

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fcFormCPartB_id", nullable = true)
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormCPartBDistrictWise> fcFormCPartBDistrictWises = new HashSet<>();

    @OneToMany(mappedBy = "fcFormCPartB")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBDistrictWise> fcFormCPartBDistrictWise = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormD")
    @Where(clause = "is_deleted ='false'")
    private List<FcFormDLegalStatus> fcFormDLegalStatus = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fcFormCPartB_id", nullable = true)
    @Where(clause = "is_deleted ='false'")
    private Set<FcFormCPartBLegalStatus> fcFormCPartBLegalStatus = new HashSet<>();

    @OneToMany(mappedBy = "fcFormCPartB", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted ='false'")
    private List<FcFormCPartBCaLand> fcFormCPartBCaLands = new ArrayList<>();

    @Column(nullable = true)
    private Boolean is_tree_cutted;

    public FcFormCPartB() {
        this.is_active = true;
        this.is_deleted = false;
        this.violation_work_inprogress = false;
        this.violation_carried_out = false;
    }

}
