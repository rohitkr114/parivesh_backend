package com.backend.model.EcForm7;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ec_form_7", schema = "master")
public class EcForm7 extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = true)
    private String proposal_no;

    @Column(nullable = true)
    private String company_name;

    @Column(nullable = true)
    private String company_house;

    @Column(nullable = true)
    private String company_village;

    @Column(nullable = true)
    private String company_district;

    @Column(nullable = true)
    private String company_state;

    @Column(nullable = true)
    private String company_pincode;

    @Column(nullable = true)
    private String company_landmark;

    @Column(nullable = true)
    private String company_email;

    @Column(nullable = true)
    private String company_landline;

    @Column(nullable = true)
    private String company_mobile;

    @Column(nullable = true)
    private String company_legal_status;

    @Column(nullable = true)
    private String transferer_company_name;

    @Column(nullable = true)
    private String transferer_company_house;

    @Column(nullable = true)
    private String transferer_company_village;

    @Column(nullable = true)
    private String transferer_company_district;

    @Column(nullable = true)
    private String transferer_company_state;

    @Column(nullable = true)
    private String transferer_company_pincode;

    @Column(nullable = true)
    private String transferer_company_landmark;

    @Column(nullable = true)
    private String transferer_company_email;

    @Column(nullable = true)
    private String transferer_company_landline;

    @Column(nullable = true)
    private String transferer_company_mobile;

    @Column(nullable = true)
    private String transferer_company_legal_status;

    @OneToMany(mappedBy = "ecForm7", cascade = CascadeType.ALL)
    @Where(clause = "isDelete = 'false'")
    List<EcForm7ProjectActivityDetails> ecForm7ProjectActivityDetails = new ArrayList<>();

    @OneToOne(mappedBy = "ecForm7", cascade = CascadeType.ALL)
    @Where(clause = "is_delete ='false'")
    private EcForm7ActivityStatus ecForm7ActivityStatus;

    @OneToOne(mappedBy = "ecForm7", cascade = CascadeType.ALL)
    @Where(clause = "is_delete ='false'")
    private EcForm7AttachedDocuments ecForm7AttachedDocuments;

    @OneToMany(targetEntity = EcForm7CafKML.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form_7_id", referencedColumnName = "id")
    @Where(clause = "is_deleted ='false'")
    private List<EcForm7CafKML> ecForm7CafKML = new ArrayList<>();

    @OneToOne(mappedBy = "ecForm7", cascade = CascadeType.ALL)
    @Where(clause = "is_delete ='false'")
    private EcForm7ProjectActivity ecForm7ProjectActivity;

    @OneToOne(mappedBy = "ecForm7")
    private EcForm7Undertaking ecForm7Undertaking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_id", nullable = true)
    @JsonIgnore
    private EnvironmentClearence environmentClearence;

    @Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer ec_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caf_id", nullable = true)
    private CommonFormDetail commonFormDetail;

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(name = "organization_plot", length = 100, nullable = true)
    private String organization_plot;

    @Column(name = "organization_village", length = 100, nullable = true)
    private String organization_village;

    @Column(name = "organization_sub_district", length = 100, nullable = true)
    private String organization_sub_district;

    @Column(name = "organization_district", length = 100, nullable = true)
    private String organization_district;

    @Column(name = "organization_state", length = 100, nullable = true)
    private String organization_state;

    @Column(name = "organization_pincode", length = 20, nullable = true)
    private String organization_pincode;

    @Column(nullable = true)
    private String organization_latitudes_ne_from;

    @Column(nullable = true)
    private String organization_latitudes_ne_to;

    @Column(nullable = true)
    private String organization_longitudes_sw_from;

    @Column(nullable = true)
    private String organization_longitudes_sw_to;

    @Column(nullable = true)
    private Boolean is_multiple_items_involved;

    @Column(name = "is_proposed_required", nullable = true)
    private Boolean is_proposed_required;

    @Column(name = "major_activity_id", nullable = true)
    private Integer major_activity_id;

    @Column(name = "major_sub_activity_id", nullable = true)
    private Integer major_sub_activity_id;

    @Column(nullable = true)
    private String project_category;

    @Column(nullable = true)
    private Boolean is_proposal_appraised;

    @Column(nullable = true)
    private String reason_for_application;

    @Column(nullable = true)
    private String reason_for_application_other;

    @Column(nullable = true)
    private String application_made_by;

    @Column(nullable = true)
    private Boolean is_changed_involved;

    @Column(nullable = true)
    private String ec_proposal_no;

    @Column(nullable = true)
    private String project_name;

    @Column(nullable = true)
    private String applicant_name;

    @Column(nullable = true)
    private String applicant_designation;

    @Column(nullable = true)
    private String applicant_house;

    @Column(nullable = true)
    private String applicant_village;

    @Column(nullable = true)
    private String applicant_district;

    @Column(nullable = true)
    private String applicant_state;

    @Column(nullable = true)
    private String applicant_pincode;

    @Column(nullable = true)
    private String applicant_landmark;

    @Column(nullable = true)
    private String applicant_email;

    @Column(nullable = true)
    private String applicant_landline;

    @Column(nullable = true)
    private String applicant_mobile;

//
//	@OneToMany(mappedBy = "ecForm7", cascade = CascadeType.ALL)
//	@Where(clause = "is_delete ='false'")
//	private List<EcForm7MinorActivity> ecForm7MinorActivities = new ArrayList<>();

    @Column(nullable = true)
    private Boolean is_delete;

    @Column(nullable = true)
    private Boolean is_active;

    public EcForm7() {
        this.is_active = true;
        this.is_delete = false;
    }

    public EcForm7(Integer id, String proposal_no, String project_category, Integer ec_id, Boolean is_proposed_required, Integer major_activity_id, Integer major_sub_activity_id) {
        this.id = id;
        this.proposal_no = proposal_no;
        this.project_category = project_category;
        this.ec_id = ec_id;
        this.is_proposed_required = is_proposed_required;
        this.major_activity_id = major_activity_id;
        this.major_sub_activity_id = major_sub_activity_id;
    }

    public EcForm7(Integer id, String proposal_no, String company_name, String company_house, String company_village, String company_district, String company_state, String company_pincode, String company_landmark, String company_email, String company_landline, String company_mobile, String company_legal_status, String transferer_company_name, String transferer_company_house, String transferer_company_village, String transferer_company_district, String transferer_company_state, String transferer_company_pincode, String transferer_company_landmark, String transferer_company_email, String transferer_company_landline, String transferer_company_mobile, String transferer_company_legal_status, Integer ec_id, Boolean is_multiple_items_involved, Boolean is_proposed_required, Integer major_activity_id, Integer major_sub_activity_id, String project_category, Boolean is_proposal_appraised, String reason_for_application, String reason_for_application_other, String application_made_by, Boolean is_changed_involved, String ec_proposal_no, String project_name, String applicant_name, String applicant_designation, String applicant_house, String applicant_village, String applicant_district, String applicant_state, String applicant_pincode, String applicant_landmark, String applicant_email, String applicant_landline, String applicant_mobile) {
        this.id = id;
        this.proposal_no = proposal_no;
        this.company_name = company_name;
        this.company_house = company_house;
        this.company_village = company_village;
        this.company_district = company_district;
        this.company_state = company_state;
        this.company_pincode = company_pincode;
        this.company_landmark = company_landmark;
        this.company_email = company_email;
        this.company_landline = company_landline;
        this.company_mobile = company_mobile;
        this.company_legal_status = company_legal_status;
        this.transferer_company_name = transferer_company_name;
        this.transferer_company_house = transferer_company_house;
        this.transferer_company_village = transferer_company_village;
        this.transferer_company_district = transferer_company_district;
        this.transferer_company_state = transferer_company_state;
        this.transferer_company_pincode = transferer_company_pincode;
        this.transferer_company_landmark = transferer_company_landmark;
        this.transferer_company_email = transferer_company_email;
        this.transferer_company_landline = transferer_company_landline;
        this.transferer_company_mobile = transferer_company_mobile;
        this.transferer_company_legal_status = transferer_company_legal_status;
        this.ec_id = ec_id;
        this.is_multiple_items_involved = is_multiple_items_involved;
        this.is_proposed_required = is_proposed_required;
        this.major_activity_id = major_activity_id;
        this.major_sub_activity_id = major_sub_activity_id;
        this.project_category = project_category;
        this.is_proposal_appraised = is_proposal_appraised;
        this.reason_for_application = reason_for_application;
        this.reason_for_application_other = reason_for_application_other;
        this.application_made_by = application_made_by;
        this.is_changed_involved = is_changed_involved;
        this.ec_proposal_no = ec_proposal_no;
        this.project_name = project_name;
        this.applicant_name = applicant_name;
        this.applicant_designation = applicant_designation;
        this.applicant_house = applicant_house;
        this.applicant_village = applicant_village;
        this.applicant_district = applicant_district;
        this.applicant_state = applicant_state;
        this.applicant_pincode = applicant_pincode;
        this.applicant_landmark = applicant_landmark;
        this.applicant_email = applicant_email;
        this.applicant_landline = applicant_landline;
        this.applicant_mobile = applicant_mobile;

    }


}
