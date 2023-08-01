package com.backend.model;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ec", schema = "master")
public class EnvironmentClearence extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "proposal_no", unique = true, length = 50, nullable = false)
    private String proposal_no;

    @Column(name = "is_multiple_item_envolved", nullable = false)
    private boolean is_multiple_item_envolved;

    @Column(name = "major_activity_id", nullable = true)
    private Integer major_activity_id;

    @Column(name = "major_sub_activity_id", nullable = true)
    private Integer major_sub_activity_id;

    @Column(name = "is_general_condition_specified", nullable = false)
    private boolean is_general_condition_specified;

    @Column(name = "project_category", length = 100, nullable = false)
    private String project_category;

    @Column(name = "central_application_reason", length = 500, nullable = true)
    private String central_application_reason;

    @Column(name = "is_interlinked_proposal", nullable = true)
    private boolean is_interlinked_proposal;

    @Column(name = "is_saperate_application_submitted", nullable = true)
    private boolean is_saperate_application_submitted;

    @Column(name = "is_saperate_application_submitted_reason", nullable = true, length = 500)
    private String is_saperate_application_submitted_reason;

    @Column(name = "interlink_proposal_no", nullable = true, length = 100)
    private String interlink_proposal_no;

    @Column(name = "interlink_date_of_submission", nullable = true)
    private Date interlink_date_of_submission;

    @Column(name = "is_interlink_ec_obtained", nullable = true)
    private boolean is_interlink_ec_obtained;

    @Column(name = "interlink_moefcc_file_no", nullable = true, length = 500)
    private String interlink_moefcc_file_no;

    @Column(name = "interlink_date_of_issue", nullable = true)
    private Date interlink_date_of_issue;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "interlink_ec_letter_id", nullable = true)
    private DocumentDetails interlink_ec_letter;

    @Column(name = "no_ec_obtained_reason", nullable = true, length = 500)
    private String no_ec_obtained_reason;

    @Column(name = "no_interlinked_proposal_reason", nullable = true, length = 300)
    private String no_interlinked_proposal_reason;

    @Column(name = "is_fc_involved", nullable = true)
    private boolean is_fc_involved;

    @Column(name = "is_fc_approval_invovled", nullable = true)
    private boolean is_fc_approval_invovled;

    @Column(name = "fc_proposal_no", nullable = true, length = 50)
    private String fc_proposal_no;

    @Column(name = "fc_moefcc_file_no", nullable = true, length = 80)
    private String fc_moefcc_file_no;

    @Column(name = "fc_date_of_approval", nullable = true)
    private Date fc_date_of_approval;

    @Column(name = "fc_area_diverted", nullable = true)
    private Double fc_area_diverted;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fc_approval_copy_id", nullable = true)
    private DocumentDetails fc_approval_copy;

    @Column(name = "fc_status_of_application", nullable = true)
    private String fc_status_of_application;

    @Column(name = "fc_submitted_proposal_no", nullable = true, length = 50)
    private String fc_submitted_proposal_no;

    @Column(name = "fc_date_of_submission", nullable = true)
    private Date fc_date_of_submission;

    @Column(name = "fc_applied_diversion", nullable = true)
    private Double fc_applied_diversion;

    @Column(name = "fc_non_submission_reason", nullable = true, length = 500)
    private String fc_non_submission_reason;

    @Column(name = "no_fc_reason", length = 500, nullable = true)
    private String no_fc_reason;

    @Column(name = "is_nbwl_recomm", nullable = true)
    private boolean is_nbwl_recomm;

    @Column(name = "nbwl_name_pa", nullable = true, length = 50)
    private String nbwl_name_pa;

    @Column(name = "nbwl_project_location", length = 50)
    private String nbwl_project_location;

    @Column(name = "is_nbwl_application_submitted", nullable = true)
    private boolean is_nbwl_application_submitted;

    @Column(name = "nbwl_proposal_no", nullable = true, length = 50)
    private String nbwl_proposal_no;

    @Column(name = "nbwl_date_of_application", nullable = true)
    private Date nbwl_date_of_application;

    @Column(name = "nbwl_status", nullable = true)
    private String nbwl_status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_nbwl_recomm_id", nullable = true)
    private DocumentDetails upload_nbwl_recomm;

    @Column(name = "nbwl_non_submission_reason", nullable = true)
    private String nbwl_non_submission_reason;

    @Column(name = "no_nbwl_recomm_reason", length = 500, nullable = true)
    private String no_nbwl_recomm_reason;

    @Column(name = "central_application_reason_other", length = 300, nullable = true)
    private String central_application_reason_other;

    @Column(name = "is_legacy_proposal", nullable = true)
    private Boolean is_legacy_proposal;

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caf_id", nullable = false)
    @JsonIgnore
    private CommonFormDetail commonFormDetail;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EC_Activity_Id", nullable = true)
    private Activities activities;

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @OneToOne(mappedBy = "enviromentClearence")
    private EcProductDetail ecProductDetail;

    @OneToOne(mappedBy = "enviromentClearence")
    private EcProjectDetail ecProjectDetail;

    @OneToOne(mappedBy = "enviromentClearence")
    private EcOthersDetail ecOthersDetail;

    @OneToOne(mappedBy = "enviromentClearence")
    private EcEnclosureDetail ecEnclosureDetail;

    @OneToOne(mappedBy = "enviromentClearence")
    private EcConsultant ecConsultant;

    @OneToMany(mappedBy = "environmentClearence", cascade = CascadeType.ALL)
    @Where(clause = "isDelete = 'false'")
    List<EnvironmentClearanceProjectActivityDetails> environmentClearanceProjectActivityDetails = new ArrayList<>();

    @OneToMany(mappedBy = "environmentClearence", cascade = CascadeType.ALL)
    @Where(clause = "isDelete = 'false'")
    List<EnvironmentClearanceProjectAreaDetails> environmentClearanceProjectAreaDetails = new ArrayList<>();

    @OneToMany(mappedBy = "environmentClearence", cascade = CascadeType.ALL)
    @Where(clause = "isDelete = 'false'")
    List<EcProdTransportDetails> ecProdTransportDetails = new ArrayList<>();

    @OneToMany(mappedBy = "environmentClearence", cascade = CascadeType.ALL)
    @Where(clause = "isDelete = 'false'")
    List<EcProjectImplementationDetails> ecProjectImplementationDetails = new ArrayList<>();

    @Column(nullable = true)
    private Boolean is_proposed_required;

    @Column(nullable = true)
    private Boolean is_protected;

    @Column(nullable = true)
    private Boolean is_critically_polluted;

    @Column(nullable = true)
    private Boolean is_eco_sensitive;

    @Column(nullable = true)
    private Boolean is_inter_state;

    @Column(nullable = true)
    private Boolean is_severely_populated;

    @Column(nullable = true)
    private String transfer_proposal_no;

    @Column(nullable = true)
    private String transfer_proposal_remarks;

    @Column(nullable = true)
    private Date transfer_proposal_date;

    @Column(columnDefinition = "Boolean default false")
    private Boolean is_for_old_proposal;

    @Column(nullable = true)
    private Boolean is_amendment;

    @Column(nullable = true)
    private Integer parent_id;
    
    @Column(nullable = true)
    private String legacy_proposal_type;

    public EcConsultant getEcConsultant() {
        return ecConsultant;
    }

    public void setEcConsultant(EcConsultant ecConsultant) {
        this.ecConsultant = ecConsultant;
    }

    public Boolean getIs_for_old_proposal() {
        return is_for_old_proposal;
    }

    public void setIs_for_old_proposal(Boolean is_for_old_proposal) {
        this.is_for_old_proposal = is_for_old_proposal;
    }

    public String getTransfer_proposal_no() {
        return transfer_proposal_no;
    }

    public void setTransfer_proposal_no(String transfer_proposal_no) {
        this.transfer_proposal_no = transfer_proposal_no;
    }

    public String getTransfer_proposal_remarks() {
        return transfer_proposal_remarks;
    }

    public void setTransfer_proposal_remarks(String transfer_proposal_remarks) {
        this.transfer_proposal_remarks = transfer_proposal_remarks;
    }

    public Date getTransfer_proposal_date() {
        return transfer_proposal_date;
    }

    public void setTransfer_proposal_date(Date transfer_proposal_date) {
        this.transfer_proposal_date = transfer_proposal_date;
    }
    
    public EnvironmentClearence() {
        this.is_multiple_item_envolved = false;
        this.is_general_condition_specified = false;
        this.is_interlinked_proposal = false;
        this.is_saperate_application_submitted = false;
        this.is_fc_involved = false;
        this.is_nbwl_recomm = false;
        this.is_legacy_proposal = false;
    }

    public EnvironmentClearence(Integer id, String proposalNo, String project_category, Boolean is_proposed_required, Integer major_activity_id, Integer major_sub_activity_id, Boolean is_for_old_proposal, Boolean is_amendment, Integer parent_id) {
        this.id = id;
        this.proposal_no = proposalNo;
        this.project_category = project_category;
        this.is_proposed_required = is_proposed_required;
        this.major_activity_id = major_activity_id;
        this.major_sub_activity_id = major_sub_activity_id;
        this.is_for_old_proposal = is_for_old_proposal;
        this.is_amendment = is_amendment;
        this.parent_id = parent_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProposal_no() {
        return proposal_no;
    }

    public void setProposal_no(String proposal_no) {
        this.proposal_no = proposal_no;
    }

    public boolean isIs_multiple_item_envolved() {
        return is_multiple_item_envolved;
    }

    public void setIs_multiple_item_envolved(boolean is_multiple_item_envolved) {
        this.is_multiple_item_envolved = is_multiple_item_envolved;
    }

    public boolean isIs_general_condition_specified() {
        return is_general_condition_specified;
    }

    public void setIs_general_condition_specified(boolean is_general_condition_specified) {
        this.is_general_condition_specified = is_general_condition_specified;
    }

    public String getProject_category() {
        return project_category;
    }

    public void setProject_category(String project_category) {
        this.project_category = project_category;
    }

    public String getCentral_application_reason() {
        return central_application_reason;
    }

    public void setCentral_application_reason(String central_application_reason) {
        this.central_application_reason = central_application_reason;
    }

    public boolean isIs_interlinked_proposal() {
        return is_interlinked_proposal;
    }

    public void setIs_interlinked_proposal(boolean is_interlinked_proposal) {
        this.is_interlinked_proposal = is_interlinked_proposal;
    }

    public String getNo_interlinked_proposal_reason() {
        return no_interlinked_proposal_reason;
    }

    public void setNo_interlinked_proposal_reason(String no_interlinked_proposal_reason) {
        this.no_interlinked_proposal_reason = no_interlinked_proposal_reason;
    }

    public boolean isIs_saperate_application_submitted() {
        return is_saperate_application_submitted;
    }

    public void setIs_saperate_application_submitted(boolean is_saperate_application_submitted) {
        this.is_saperate_application_submitted = is_saperate_application_submitted;
    }

    public String getNo_ec_obtained_reason() {
        return no_ec_obtained_reason;
    }

    public void setNo_ec_obtained_reason(String no_ec_obtained_reason) {
        this.no_ec_obtained_reason = no_ec_obtained_reason;
    }

    public boolean isIs_fc_involved() {
        return is_fc_involved;
    }

    public void setIs_fc_involved(boolean is_fc_involved) {
        this.is_fc_involved = is_fc_involved;
    }

    public String getNo_fc_reason() {
        return no_fc_reason;
    }

    public void setNo_fc_reason(String no_fc_reason) {
        this.no_fc_reason = no_fc_reason;
    }

    public boolean isIs_nbwl_recomm() {
        return is_nbwl_recomm;
    }

    public void setIs_nbwl_recomm(boolean is_nbwl_recomm) {
        this.is_nbwl_recomm = is_nbwl_recomm;
    }

    public String getNo_nbwl_recomm_reason() {
        return no_nbwl_recomm_reason;
    }

    public void setNo_nbwl_recomm_reason(String no_nbwl_recomm_reason) {
        this.no_nbwl_recomm_reason = no_nbwl_recomm_reason;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public CommonFormDetail getCommonFormDetail() {
        return commonFormDetail;
    }

    public void setCommonFormDetail(CommonFormDetail commonFormDetail) {
        this.commonFormDetail = commonFormDetail;
    }

    public List<EnvironmentClearanceProjectActivityDetails> getEnvironmentClearanceProjectActivityDetails() {
        return environmentClearanceProjectActivityDetails;
    }

    public void setEnvironmentClearanceProjectActivityDetails(List<EnvironmentClearanceProjectActivityDetails> environmentClearanceProjectActivityDetails) {
        this.environmentClearanceProjectActivityDetails = environmentClearanceProjectActivityDetails;
    }

    public List<EnvironmentClearanceProjectAreaDetails> getEnvironmentClearanceProjectAreaDetails() {
        return environmentClearanceProjectAreaDetails;
    }

    public void setEnvironmentClearanceProjectAreaDetails(List<EnvironmentClearanceProjectAreaDetails> environmentClearanceProjectAreaDetails) {
        this.environmentClearanceProjectAreaDetails = environmentClearanceProjectAreaDetails;
    }

    public boolean isIs_interlink_ec_obtained() {
        return is_interlink_ec_obtained;
    }

    public void setIs_interlink_ec_obtained(boolean is_interlink_ec_obtained) {
        this.is_interlink_ec_obtained = is_interlink_ec_obtained;
    }

    public boolean isIs_fc_approval_invovled() {
        return is_fc_approval_invovled;
    }

    public void setIs_fc_approval_invovled(boolean is_fc_approval_invovled) {
        this.is_fc_approval_invovled = is_fc_approval_invovled;
    }

    public boolean isIs_nbwl_application_submitted() {
        return is_nbwl_application_submitted;
    }

    public void setIs_nbwl_application_submitted(boolean is_nbwl_application_submitted) {
        this.is_nbwl_application_submitted = is_nbwl_application_submitted;
    }

    public ProponentApplications getProponentApplications() {
        return proponentApplications;
    }

    public void setProponentApplications(ProponentApplications proponentApplications) {
        this.proponentApplications = proponentApplications;
    }

    public EcProductDetail getEcProductDetail() {
        return ecProductDetail;
    }

    public void setEcProductDetail(EcProductDetail ecProductDetail) {
        this.ecProductDetail = ecProductDetail;
    }

    public EcProjectDetail getEcProjectDetail() {
        return ecProjectDetail;
    }

    public void setEcProjectDetail(EcProjectDetail ecProjectDetail) {
        this.ecProjectDetail = ecProjectDetail;
    }

    public EcOthersDetail getEcOthersDetail() {
        return ecOthersDetail;
    }

    public void setEcOthersDetail(EcOthersDetail ecOthersDetail) {
        this.ecOthersDetail = ecOthersDetail;
    }

    public EcEnclosureDetail getEcEnclosureDetail() {
        return ecEnclosureDetail;
    }

    public void setEcEnclosureDetail(EcEnclosureDetail ecEnclosureDetail) {
        this.ecEnclosureDetail = ecEnclosureDetail;
    }

    public List<EcProdTransportDetails> getEcProdTransportDetails() {
        return ecProdTransportDetails;
    }

    public void setEcProdTransportDetails(List<EcProdTransportDetails> ecProdTransportDetails) {
        this.ecProdTransportDetails = ecProdTransportDetails;
    }

    public List<EcProjectImplementationDetails> getEcProjectImplementationDetails() {
        return ecProjectImplementationDetails;
    }

    public void setEcProjectImplementationDetails(List<EcProjectImplementationDetails> ecProjectImplementationDetails) {
        this.ecProjectImplementationDetails = ecProjectImplementationDetails;
    }

    public Integer getMajor_activity_id() {
        return major_activity_id;
    }

    public void setMajor_activity_id(Integer major_activity_id) {
        this.major_activity_id = major_activity_id;
    }

    public String getInterlink_proposal_no() {
        return interlink_proposal_no;
    }

    public void setInterlink_proposal_no(String interlink_proposal_no) {
        this.interlink_proposal_no = interlink_proposal_no;
    }

    public Date getInterlink_date_of_submission() {
        return interlink_date_of_submission;
    }

    public void setInterlink_date_of_submission(Date interlink_date_of_submission) {
        this.interlink_date_of_submission = interlink_date_of_submission;
    }

    public String getInterlink_moefcc_file_no() {
        return interlink_moefcc_file_no;
    }

    public void setInterlink_moefcc_file_no(String interlink_moefcc_file_no) {
        this.interlink_moefcc_file_no = interlink_moefcc_file_no;
    }

    public Date getInterlink_date_of_issue() {
        return interlink_date_of_issue;
    }

    public void setInterlink_date_of_issue(Date interlink_date_of_issue) {
        this.interlink_date_of_issue = interlink_date_of_issue;
    }

    public DocumentDetails getInterlink_ec_letter() {
        return interlink_ec_letter;
    }

    public void setInterlink_ec_letter(DocumentDetails interlink_ec_letter) {
        this.interlink_ec_letter = interlink_ec_letter;
    }

    public String getFc_proposal_no() {
        return fc_proposal_no;
    }

    public void setFc_proposal_no(String fc_proposal_no) {
        this.fc_proposal_no = fc_proposal_no;
    }

    public String getFc_moefcc_file_no() {
        return fc_moefcc_file_no;
    }

    public void setFc_moefcc_file_no(String fc_moefcc_file_no) {
        this.fc_moefcc_file_no = fc_moefcc_file_no;
    }

    public Date getFc_date_of_approval() {
        return fc_date_of_approval;
    }

    public void setFc_date_of_approval(Date fc_date_of_approval) {
        this.fc_date_of_approval = fc_date_of_approval;
    }

    public Double getFc_area_diverted() {
        return fc_area_diverted;
    }

    public void setFc_area_diverted(Double fc_area_diverted) {
        this.fc_area_diverted = fc_area_diverted;
    }

    public DocumentDetails getFc_approval_copy() {
        return fc_approval_copy;
    }

    public void setFc_approval_copy(DocumentDetails fc_approval_copy) {
        this.fc_approval_copy = fc_approval_copy;
    }

    public String getFc_status_of_application() {
        return fc_status_of_application;
    }

    public void setFc_status_of_application(String fc_status_of_application) {
        this.fc_status_of_application = fc_status_of_application;
    }

    public String getFc_submitted_proposal_no() {
        return fc_submitted_proposal_no;
    }

    public void setFc_submitted_proposal_no(String fc_submitted_proposal_no) {
        this.fc_submitted_proposal_no = fc_submitted_proposal_no;
    }

    public Date getFc_date_of_submission() {
        return fc_date_of_submission;
    }

    public void setFc_date_of_submission(Date fc_date_of_submission) {
        this.fc_date_of_submission = fc_date_of_submission;
    }

    public Double getFc_applied_diversion() {
        return fc_applied_diversion;
    }

    public void setFc_applied_diversion(Double fc_applied_diversion) {
        this.fc_applied_diversion = fc_applied_diversion;
    }

    public String getFc_non_submission_reason() {
        return fc_non_submission_reason;
    }

    public void setFc_non_submission_reason(String fc_non_submission_reason) {
        this.fc_non_submission_reason = fc_non_submission_reason;
    }

    public String getNbwl_name_pa() {
        return nbwl_name_pa;
    }

    public void setNbwl_name_pa(String nbwl_name_pa) {
        this.nbwl_name_pa = nbwl_name_pa;
    }

    public String getNbwl_project_location() {
        return nbwl_project_location;
    }

    public void setNbwl_project_location(String nbwl_project_location) {
        this.nbwl_project_location = nbwl_project_location;
    }

    public String getNbwl_proposal_no() {
        return nbwl_proposal_no;
    }

    public void setNbwl_proposal_no(String nbwl_proposal_no) {
        this.nbwl_proposal_no = nbwl_proposal_no;
    }

    public Date getNbwl_date_of_application() {
        return nbwl_date_of_application;
    }

    public void setNbwl_date_of_application(Date nbwl_date_of_application) {
        this.nbwl_date_of_application = nbwl_date_of_application;
    }

    public String getNbwl_status() {
        return nbwl_status;
    }

    public void setNbwl_status(String nbwl_status) {
        this.nbwl_status = nbwl_status;
    }

    public DocumentDetails getUpload_nbwl_recomm() {
        return upload_nbwl_recomm;
    }

    public void setUpload_nbwl_recomm(DocumentDetails upload_nbwl_recomm) {
        this.upload_nbwl_recomm = upload_nbwl_recomm;
    }

    public String getNbwl_non_submission_reason() {
        return nbwl_non_submission_reason;
    }

    public void setNbwl_non_submission_reason(String nbwl_non_submission_reason) {
        this.nbwl_non_submission_reason = nbwl_non_submission_reason;
    }

    public String getIs_saperate_application_submitted_reason() {
        return is_saperate_application_submitted_reason;
    }

    public void setIs_saperate_application_submitted_reason(String is_saperate_application_submitted_reason) {
        this.is_saperate_application_submitted_reason = is_saperate_application_submitted_reason;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public Boolean getIs_proposed_required() {
        return is_proposed_required;
    }

    public void setIs_proposed_required(Boolean is_proposed_required) {
        this.is_proposed_required = is_proposed_required;
    }

    public Boolean getIs_protected() {
        return is_protected;
    }

    public void setIs_protected(Boolean is_protected) {
        this.is_protected = is_protected;
    }

    public Boolean getIs_critically_polluted() {
        return is_critically_polluted;
    }

    public void setIs_critically_polluted(Boolean is_critically_polluted) {
        this.is_critically_polluted = is_critically_polluted;
    }

    public Boolean getIs_eco_sensitive() {
        return is_eco_sensitive;
    }

    public void setIs_eco_sensitive(Boolean is_eco_sensitive) {
        this.is_eco_sensitive = is_eco_sensitive;
    }

    public Boolean getIs_inter_state() {
        return is_inter_state;
    }

    public void setIs_inter_state(Boolean is_inter_state) {
        this.is_inter_state = is_inter_state;
    }

    public Boolean getIs_severely_populated() {
        return is_severely_populated;
    }

    public void setIs_severely_populated(Boolean is_severely_populated) {
        this.is_severely_populated = is_severely_populated;
    }

    public Integer getMajor_sub_activity_id() {
        return major_sub_activity_id;
    }

    public void setMajor_sub_activity_id(Integer major_sub_activity_id) {
        this.major_sub_activity_id = major_sub_activity_id;
    }

    public String getCentral_application_reason_other() {
        return central_application_reason_other;
    }

    public void setCentral_application_reason_other(String central_application_reason_other) {
        this.central_application_reason_other = central_application_reason_other;
    }

    public Boolean getIs_amendment() {
        return is_amendment;
    }

    public void setIs_amendment(Boolean is_amendment) {
        this.is_amendment = is_amendment;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }


}
