package com.backend.model.EcForm13;

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="ec_form13_project_details",schema = "master")
public class EcForm13ProjectDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="major_activity_id")
    private Integer majorActivityId;

    @Column(name = "major_sub_activity_id", nullable = true)
    private Integer majorSubActivityId;

    @Column(name="is_for_old_proposal",nullable=true)
    private Boolean isForOldProposal;

    //Details of Project
    @Column(name = "proposal_no")
    private String proposalNo;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "proposal_for")
    private String proposalFor;

    @Column(name = "seiaa_proposal_no")
    private String seiaaProposalNo;

    @Column(name = "ec_submission_date")
    private Date ecSubmissionDate;

    @Column(name = "legal_status")
    private String legalStatus;

    private String description;


    //current status of project
    @Column(name = "current_status")
    private String currentStatus;

    @Column(name = "proposal_involves_public_interest")
    private Boolean proposalInvolvesPublicInterest;

    @Lob
    private String details;

    @Lob
    private String reason;

    @Column(name = "ec_pendency")
    private Integer ecPendency;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "endorsement_copy_id", nullable = true)
    private DocumentDetails endorsementCopy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "proposal_chronology_id", nullable = true)
    private DocumentDetails proposalChronology;

    @Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer ec_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_id", nullable = true)
    @JsonIgnore
    private EcPartC ecPartc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tor_id", nullable = true)
    @JsonIgnore
    private EnvironmentClearence environmentClearence;

    @Column(name = "tor_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer tor_id;

    @OneToMany(mappedBy="ecForm13",  cascade = CascadeType.ALL)
    @Where(clause="is_deleted=false")
    private List<EcForm13ProjectActivityDetails> ecForm13ProjectActivityDetails= new ArrayList<>();

    @OneToOne(mappedBy = "ecForm13",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private EcForm13ProjectSpecificDetails ecForm13ProjectSpecificDetails;

    @OneToOne(mappedBy = "ecForm13",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private EcForm13ConsultantDetails ecForm13ConsultantDetails;

    @OneToOne(mappedBy = "ecForm13",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private EcForm13EnclosureDetails ecForm13EnclosureDetails;

    @OneToOne(mappedBy = "ecForm13",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private EcForm13Undertaking ecForm13Undertaking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caf_id", nullable = false)
    @JsonIgnore
    private CommonFormDetail commonFormDetail;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
