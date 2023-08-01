package com.backend.model.FcFactsheet;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fc_factsheet",schema = "authority")
public class FcFactsheet extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="application_id",nullable = false)
    private Integer applicationId;

    @Column(name="proposal_no")
    private String proposalNo;

    @Column(name="proposal_name")
    private String proposalName;

    @Column(name="state_name")
    private String stateName;

    @Column(name="state_code")
    private Integer stateCode;

    @Column(name="district_name")
    private String districtName;

    @Column(name="district_code")
    private Integer districtCode;

    @Column(name="forest_division")
    private String forestDivision;

    @Column(name="forest_area")
    private Double forestArea;

    @Column(name="non_forest_area")
    private Double nonForestArea;

    @Column(name="proposal_category")
    private String proposalCategory;

    @Column(name="agency_name")
    private String agencyName;

    @Column(name="forest_legal_status")
    private String forestLegalStatus;

    @Column(name="distance_of_proposed_site")
    private Double distanceOfProposedSite;

    @Column(name="is_near_archaeological_site")
    private Boolean isNearArchaeologicalSite;

    @Column(name="is_violation")
    private Boolean isViolation;

    @Column(name="is_environment_clearance_required")
    private Boolean isEnvironmentClearanceRequired;

    @Column(name="non_forest_area_details")
    private String nonForestAreaDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "details_of_species_id", nullable = true)
    private DocumentDetails detailsOfSpecies;

    @Column(name="total_financial_outlay")
    private Double totalFinancialOutlay;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "certificate_id", nullable = true)
    private DocumentDetails certificate;

    private String rehabilitation;

    @Column(name = "no_of_families_involved_in_rehabilitation")
    private Integer noOfFamiliesInvolvedInRehabilitation;

    @Column(name="no_of_sc_families")
    private Integer noOfSCFamilies;

    @Column(name="no_of_st_families")
    private Integer noOfSTFamilies;

    @Column(name="no_of_backward_families")
    private Integer noOfBackwardFamilies;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "planCopy_id", nullable = true)
    private DocumentDetails planCopy;

    @Column(name="employment_generated")
    private Integer employmentGeneratedConstruction;

    @Column(name="employment_generated_operational")
    private Integer employmentGeneratedOperational;
    
    @Column(name="cost_benefit_ratio")
    private Double costBenefitRatio;

    @Column(name="total_cost")
    private Double totalCost;

    private String status;

    //new changes

    @Column(name = "average_canopy_density")
    private Double averageCanopyDensity;

    @Column(name = "total_trees")
    private Integer totalTrees;

    @Column(name = "felled_trees")
    private Integer felledTrees;

    @Column(name="proposal_state_name")
    private String proposalStateName;

    @Column(name="proposal_state_code")
    private Integer proposalStateCode;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DepartmentApplication departmentApplication;

}
