package com.backend.model.EcForm13;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="ec_form13_project_specific_details",schema = "master")
public class EcForm13ProjectSpecificDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="is_multiple_item_involved",nullable=true)
    private Boolean isMultipleItemInvolved;

    @Column(name = "project_category")
    private String projectCategory;

    @Column(name = "nature_of_tor")
    private String natureOfTOR;

    // If ToR prescribed by EAC and Ministry or Standard ToR issued by the Ministry

    @Column(name = "date_of_tor")
    private Date dateOfTOR;

    @Column(name = "date_of_additional_tor")
    private Date dateOfAdditionalTOR;

    @Column(name = "file_no")
    private String fileNo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tor_copy_id", nullable = true)
    private DocumentDetails torCopy;

    @Column(name = "is_tor_amendment_obtained")
    private Boolean isTORAmendmentObtained;

    //if amendment of TOR obtained

    @Column(name = "amendment_of_tor_issue_date")
    private Date amendmentOfTORIsuueDate;

    @Column(name = "amendment_details")
    private String amendmentDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tor_amendment_copy_id", nullable = true)
    private DocumentDetails torAmendmentCopy;

    @Column(name = "is_public_hearing_exempted")
    private Boolean isPublicHearingExempted;

    @Column(name = "public_hearing_exemption_reason")
    private String publicHearingExemptionReason;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "eac_recommendation_id", nullable = true)
    private DocumentDetails eacRecommendation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ec_form13_id",nullable = false)
    @JsonIgnore
    private EcForm13ProjectDetails ecForm13;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;


}
