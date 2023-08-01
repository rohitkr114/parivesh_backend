package com.backend.model.EcPartC;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.DocumentDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "ec_partC", schema = "master")
public class EcPartC extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_id", nullable = true)
    @JsonIgnore
    private EnvironmentClearence environmentClearence;

    @Column(name = "ec_id", nullable = false, updatable = false, insertable = false)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer ec_id;

    @Column(name = "proposal_no", nullable = true)
    private String proposal_no;

    @Column(name = "major_activity_id", nullable = true)
    private Integer major_activity_id;

    @Column(name = "major_sub_activity_id", nullable = true)
    private Integer major_sub_activity_id;

    @Column(name = "project_category", length = 100, nullable = true)
    private String project_category;

    @Column(name = "is_proposed_required", nullable = true)
    private Boolean is_proposed_required;

    @Column(nullable = true)
    private Boolean is_amendment;

    @Column(nullable = true)
    private Integer parent_id;

    @Column(name = "central_application_reason", length = 255, nullable = true)
    private String central_application_reason;

    @Column(name = "central_application_reason_other", length = 300, nullable = true)
    private String central_application_reason_other;

    @Column(name = "date_of_issue_tor", nullable = true)
    private Date date_of_issue_tor;

    @Column(name = "date_of_issue_additional_tor", nullable = true)
    private Date date_of_issue_additional_tor;

    @Column(name = "moef_file_no", nullable = true, length = 100)
    private String moef_file_no;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tor_letter_id", nullable = true)
    private DocumentDetails tor_letter;

    @Column(name = "is_any_amendment_tor", nullable = true)
    private Boolean is_any_amendment_tor;

    @Column(name = "date_of_issue_amendment_tor", nullable = true)
    private Date date_of_issue_amendment_tor;

    @Column(name = "amendment_details", nullable = true, length = 200)
    private String amendment_details;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tor_letter_copy_id", nullable = true)
    private DocumentDetails tor_letter_copy;

    @Column(name = "is_project_exempted", nullable = true)
    private Boolean is_project_exempted;

    @Column(name = "project_exempted_reason", nullable = true, length = 255)
    private String project_exempted_reason;

    @Column(name = "reason_other_field_name", nullable = true, length = 255)
    private String reason_other_field_name;

    @Column(name = "reason_other_field", nullable = true, length = 255)
    private String reason_other_field;

    @Column(name = "nature_of_tor", nullable = true, length = 255)
    private String nature_of_tor;

    @Column(name = "is_for_old_proposal")
    private Boolean is_for_old_proposal;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "eac_recommendation_id", nullable = true)
    private DocumentDetails eac_recommendation;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "public_hearing_id", nullable = true)
    @Where(clause = "is_deleted='false'")
    private Set<EcPublicHearing> ecPublicHearings = new HashSet<>();

    @Column(name = "no_of_written_comments", nullable = true)
    private String no_of_written_comments;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "major_issues_id", nullable = true)
    @Where(clause = "is_deleted='false'")
    private Set<EcMajorIssuesRaised> ecMajorIssuesRaiseds = new HashSet<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcAmbientAirQuality> ecAmbientAirQualities = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcBaseLineCollections> ecBaseLineCollections = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcSurfaceWaterQuality> ecSurfaceWaterQualities = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcGroundWaterQuality> ecGroundWaterQualities = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcGroundWaterLevel> ecGroundWaterLevels = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcNoiseLevel> ecNoiseLevels = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcSoilQuality> ecSoilQualities = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcChemicalProperties> ecChemicalProperties = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcAirQualityImpacts> ecAirQualityImpacts = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcSummaryAllocations> ecSummaryAllocations = new ArrayList<>();

    @OneToMany(mappedBy = "ecPartC")
    @Where(clause = "is_deleted='false'")
    private List<EcParameterMonitor> ecParameterMonitors = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "action_plan_raised_id", nullable = true)
    private DocumentDetails action_plan_raised;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_document_id", nullable = true)
    private DocumentDetails additional_document;

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @OneToOne(mappedBy = "ecPartC")
    private EcBaseLineDetails ecBaseLineDetails;

    @OneToOne(mappedBy = "ecPartC")
    private EcOtherDetails ecOtherDetails;

    @OneToOne(mappedBy = "ecPartC")
    private EcEnclosures ecEnclosures;

    @OneToOne(mappedBy = "ecPartC")
    private EcUndertaking ecUndertaking;

    @Column(name = "is_legacy_proposal", nullable = true)
    private Boolean is_legacy_proposal;

    public EcPartC() {
        this.is_legacy_proposal = false;
    }


    public EcPartC(Integer id, Integer ec_id, String proposal_no, Integer major_activity_id, Integer major_sub_activity_id,
                   String project_category, Boolean is_proposed_required, Boolean is_amendment, Integer parent_id, Boolean is_legacy_proposal) {
        this.id = id;
        this.ec_id = ec_id;
        this.proposal_no = proposal_no;
        this.major_activity_id = major_activity_id;
        this.major_sub_activity_id = major_sub_activity_id;
        this.project_category = project_category;
        this.is_proposed_required = is_proposed_required;
        this.is_amendment = is_amendment;
        this.parent_id = parent_id;
        this.is_legacy_proposal = is_legacy_proposal;
    }

    public EcPartC(Integer id, Integer ec_id, String proposal_no, Integer major_activity_id, Integer major_sub_activity_id,
                   String project_category, Boolean is_proposed_required, String central_application_reason,
                   String central_application_reason_other, Date date_of_issue_tor, Date date_of_issue_additional_tor,
                   String moef_file_no, Boolean is_any_amendment_tor, Date date_of_issue_amendment_tor,
                   String amendment_details, Boolean is_project_exempted, String project_exempted_reason,
                   String reason_other_field_name, String reason_other_field, String nature_of_tor, Boolean is_for_old_proposal, String no_of_written_comments, Boolean is_amendment, Integer parent_id, Boolean is_legacy_proposal) {
        this.id = id;
        this.ec_id = ec_id;
        this.proposal_no = proposal_no;
        this.major_activity_id = major_activity_id;
        this.major_sub_activity_id = major_sub_activity_id;
        this.project_category = project_category;
        this.is_proposed_required = is_proposed_required;
        this.central_application_reason = central_application_reason;
        this.central_application_reason_other = central_application_reason_other;
        this.date_of_issue_tor = date_of_issue_tor;
        this.date_of_issue_additional_tor = date_of_issue_additional_tor;
        this.moef_file_no = moef_file_no;
        this.is_any_amendment_tor = is_any_amendment_tor;
        this.date_of_issue_amendment_tor = date_of_issue_amendment_tor;
        this.amendment_details = amendment_details;
        this.is_project_exempted = is_project_exempted;
        this.project_exempted_reason = project_exempted_reason;
        this.reason_other_field_name = reason_other_field_name;
        this.reason_other_field = reason_other_field;
        this.nature_of_tor = nature_of_tor;
        this.is_for_old_proposal = is_for_old_proposal;
        this.no_of_written_comments = no_of_written_comments;
        this.is_amendment = is_amendment;
        this.parent_id = parent_id;
        this.is_legacy_proposal = is_legacy_proposal;
    }

    public EcPartC(Integer id, Integer ec_id, String proposal_no, Integer major_activity_id, Integer major_sub_activity_id,
                   String project_category, Boolean is_proposed_required, String central_application_reason,
                   String central_application_reason_other, String amendment_details, Boolean is_legacy_proposal,Boolean is_for_old_proposal ) {
        this.id = id;
        this.ec_id = ec_id;
        this.proposal_no = proposal_no;
        this.major_activity_id = major_activity_id;
        this.major_sub_activity_id = major_sub_activity_id;
        this.project_category = project_category;
        this.is_proposed_required = is_proposed_required;
        this.central_application_reason = central_application_reason;
        this.central_application_reason_other = central_application_reason_other;
        this.amendment_details = amendment_details;
        this.is_legacy_proposal = is_legacy_proposal;
        this.is_for_old_proposal = is_for_old_proposal;
    }

}
