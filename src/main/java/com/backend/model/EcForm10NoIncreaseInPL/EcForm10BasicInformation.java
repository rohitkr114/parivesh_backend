package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.EcForm7.EcForm7ProjectActivityDetails;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * It consists of fields from EC Specification point 1 to 1.2.3.1.6
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_basic_information", schema = "master")
public class EcForm10BasicInformation extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /*
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ec_form10_project_details_id", nullable = true)
        @JsonIgnore
        private EcForm10ProjectDetails ecForm10ProjectDetails;
    */
    @Column(name = "is_project_accorded")
    private String is_project_accorded;

    @Column(name = "proposal_no", nullable = true)
    private String proposal_no;

    @Column(name = "proposal_no_for_apply", nullable = true)
    private String proposal_no_for_apply;
    
    private Boolean is_multiple_items_involved;

    @Column(name = "name_of_project", nullable = true, length = 200)
    private String name_of_project;

    @Column(name = "sector", nullable = true, length = 50)
    private String sector;

    @Column(name = "activities_id", nullable = true, length = 50)
    private Integer major_activity_id;

    @Column(name = "subActivity_id", nullable = true, length = 50)
    private Integer major_sub_activity_id;

    @OneToMany(mappedBy = "ecForm10BasicInformation", cascade = CascadeType.ALL)
    @Where(clause = "isDelete = 'false'")
    List<EcForm10ProjectActivityDetails> ecForm10ProjectActivityDetails = new ArrayList<>();


    @Column(name = "sector_id", nullable = true, length = 50)
    private Integer sector_id;

    @Column(name = "proposal_for_category", nullable = true)
    private String proposal_for_category;

    @Column(name = "activity_provisions_under")
    private String activity_provisions_under;
    @Column(name = "project_falls_in_category_of_processing")
    private String is_category_processing;

    @Column(name = "is_eia", nullable = true, length = 200)
    private String is_eia;

    @Column(name = "project_falls_in_b2_category", length = 10)
    private String is_project_falls_on_b2;

    @Column(name = "instant_proposal_tantamount_to_change")
    private String instant_proposal;

    @Column(name = "project_proposed_located_in_notified_area")
    private String is_project_located_notified_area;

    @Column(name = "type_of_industrial_area", nullable = true)
    private String industrial_area;

    @Column(name = "notified_industrial", nullable = true)
    private String notified_industrial;

    @Column(name = "name_of_notified_area", nullable = true, length = 200)
    private String is_industrial;

    @Column(name = "industrial_area_notified_before", nullable = true, length = 50)
    private String is_project_industrial_area;

    @Column(name = "ec_file_no", nullable = true, length = 50)
    private String ec_file;

    @Column(name = "ec_file_after", nullable = true, length = 50)
    private String ec_file_after;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_copy_of_industrial_area", nullable = true)
    private DocumentDetails upload_notification;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_ec_letter", nullable = true)
    private DocumentDetails upload_notification_after;

    @Column(name = "prior_environmental_clearance_available")
    private String litigation_pending;

    @Column(name = "is_instant_proposal")
    private String is_instant_proposal;

    @Column(name = "moefcc_seiaa", nullable = true)
    private String moefcc;

    @Column(name = "date_of_grant", nullable = true)
    private String date_of_grant;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_consent_order", nullable = true)
    private DocumentDetails copy_consent_order;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_ec", nullable = true)
    private DocumentDetails copy_of_ec;

    @Column(name = "consent_no", nullable = true)
    private String consent_no;

    @Column(name = "consent_date", nullable = true)
    private String date;

    @Column(name = "valid_upto", nullable = true)
    private String valid_upto;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_consent_order", nullable = true)
    private DocumentDetails copy_of_consent_order;

    @Column(name = "authorization_no", nullable = true, length = 50)
    private String authorization_no;

    @Column(name = "authorization_date", nullable = true)
    private String date_hazardous;

    @Column(name = "authorization_valid_upto", nullable = true)
    private String valid_upto_date_hazardous;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_authorization_order", nullable = true)
    private DocumentDetails copy_authorization;

    @OneToMany(mappedBy = "ecForm10BasicInformation",cascade = CascadeType.ALL)
    @Where(clause = "is_delete = false")
    private List<EcForm10ConsentUnderAirDetails> consetUnderAirDetails=new ArrayList<>();

    @OneToMany(mappedBy = "ecForm10BasicInformation",cascade = CascadeType.ALL)
    @Where(clause = "is_delete = false")
    private List<EcForm10AuthorizationHazardousDetails> authorizationHazadousDetails=new ArrayList<>();

    @OneToOne(mappedBy = "ecForm10BasicInformation")
    private EcForm10ProductDetails ecForm10ProductDetails;

    @OneToOne(mappedBy = "ecForm10BasicInformation")
    private EcForm10EmissionGeneration ecForm10EmissionGeneration;

    @OneToOne(mappedBy = "ecForm10BasicInformation")
    private EcForm10HazardousWasteGeneration ecForm10HazardousWasteGeneration;

    @OneToOne(mappedBy = "ecForm10BasicInformation")
    private EcForm10AdditionalDocument ecForm10AdditionalDocument;

    @OneToOne(mappedBy = "ecForm10BasicInformation")
    private EcForm10Undertaking ecForm10Undertaking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caf_id", nullable = true)
    @JsonIgnore
    private CommonFormDetail commonFormDetail;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    public EcForm10BasicInformation(Integer id, String proposal_no) {
        this.id = id;
        this.proposal_no = proposal_no;
    }


}
