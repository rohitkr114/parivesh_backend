package com.backend.model;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "forest_clearance", schema = "master")
public class ForestClearance extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String project_activity_id;

    @Column(nullable = true, length = 500)
    private String project_activity_id_other;

    @Column(nullable = true)
    private String project_category_id;

    @Column(nullable = false)
    private Boolean is_any_prior_approval;

    @Column(name = "state")
    private Integer state;

    @Column(name = "proposal_no", unique = true, length = 50, nullable = false)
    private String proposal_no;

    @Column(name="amendment_nature",length = 1000)
    private String amendment_nature;

    @Column(name="amendment_nature_other")
    private String amendment_nature_other;
    
    @Column(length=2000)
    private String amendment_justification;

    @OneToOne(mappedBy = "forestClearance")
    private ForestProposedLand forestProposedLand;

    @OneToOne(mappedBy = "forestClearance")
    private FcAforestationDetails fcAforestationDetails;

    @OneToOne(mappedBy = "forestClearance")
    private FcOthersDetail fcOthersDetail;

    @OneToOne(mappedBy = "forestClearance")
    private FcEnclosures fcEnclosures;

    @OneToMany(mappedBy = "forestClearance")
    @Fetch(FetchMode.SUBSELECT)
    List<ForestClearanceAirportProposal> forestClearanceAirportProposals = new ArrayList<>();

    @OneToOne(mappedBy = "forestClearance")
    private ForestClearanceRiverValleyProject forestClearanceRiverValleyProject;

    @OneToOne(mappedBy = "forestClearance")
    private ForestClearanceMiningProposals forestClearanceMiningProposals;

    @OneToOne(mappedBy = "forestClearance")
    private MiningMineralOilProposal miningMineralOilProposal;

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @OneToOne(targetEntity = CommonFormDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "caf_id", nullable = false)
    private CommonFormDetail commonFormDetail;

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Where(clause = "isDelete='false'")
    @Fetch(FetchMode.SELECT)
    List<PriorApprovals> priorApprovals = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "isDelete='false'")
    List<ForestClearanceMaps> forestClearanceMaps = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "is_deleted='false'")
    List<ForestClearancePatchKmls> forestClearancePatchKmls = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "isDelete='false'")
    List<ForestClearanceProposedDiversions> forestClearanceProposedDiversions = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted='false'")
    @Fetch(FetchMode.SELECT)
    List<ForestClearanceCroppingPattern> irrigationFcCroppingPattern = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted='false'")
    @Fetch(FetchMode.SELECT)
    List<ForestClearanceIrrigationProjectCapacityVillages> fcIrrigationProjectCapacityVillage = new ArrayList<>();

    @OneToMany(mappedBy = "forestClearance", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted='false'")
    @Fetch(FetchMode.SELECT)
    List<ForestClearanceSubmergedArea> fcSubmergedArea = new ArrayList<>();


    @OneToOne(mappedBy = "forestClearance")
    FCUndertaking fcUndertaking;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "locating_project_justification_id", nullable = true)
    private DocumentDetails locating_project_justification;

    @Column(nullable = true)
    private Integer parent_id;

    private Boolean is_active;

    private Boolean is_delete;

    public ForestClearance() {
        this.is_active = true;
        this.is_delete = false;
    }

}
