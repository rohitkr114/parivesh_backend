package com.backend.model;

import com.backend.audit.Auditable;
import com.backend.dto.CertificateConditionDTO;
import com.backend.dto.ProjectDetailDto;
import com.backend.dto.UpdatedUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Date;

@NamedNativeQueries({
        @NamedNativeQuery(name = "ProponentApplicationRepository.findGeneralCondition", query = "select cle.proposal_no as proposalNo,clgc.id as conditionId,condition_type as conditionType,condition_discription as conditiondescription\r\n"
                + "from ua.clearance_letter_entity cle\r\n"
                + "join ua.clearance_letter_general_condition clgc on cle.id=clgc.general_condition_collection_ids\r\n"
                + " join master.proponent_applications pa\r\n" + "on cle.proposal_no=pa.proposal_no\r\n"
                + "and pa.id=?\r\n"
                + "group by cle.proposal_no,condition_type,clgc.id,condition_discription;", resultSetMapping = "Mapping.CertificateConditionDTO"),

        @NamedNativeQuery(name = "ProponentApplicationRepository.findOtherSpecificCondition", query = "select cle.proposal_no as proposalNo,closc.id as conditionId,condition_type as conditionType,condition_discription  as conditiondescription\r\n"
                + "from ua.clearance_letter_entity cle\r\n"
                + "join ua.clearance_letter_other_specific_condition closc on cle.id=closc.other_specific_condition_collection_ids\r\n"
                + " join master.proponent_applications pa\r\n" + "on cle.proposal_no=pa.proposal_no\r\n"
                + "and pa.id=?\r\n"
                + "group by cle.proposal_no,condition_type,closc.id,condition_discription;", resultSetMapping = "Mapping.CertificateConditionDTO"),

        @NamedNativeQuery(name = "ProponentApplicationRepository.findSpecificCondition", query = "select cle.proposal_no as proposalNo,clsc.id as conditionId,condition_type as conditionType,condition_discription  as conditiondescription\r\n"
                + "from ua.clearance_letter_entity cle\r\n"
                + "join ua.clearance_letter_specific_condition clsc on cle.id=clsc.specific_condition_collection_ids\r\n"
                + " join master.proponent_applications pa\r\n" + "on cle.proposal_no=pa.proposal_no\r\n"
                + "and pa.id=?\r\n"
                + "group by cle.proposal_no,condition_type,clsc.id,condition_discription;", resultSetMapping = "Mapping.CertificateConditionDTO"),

        @NamedNativeQuery(name = "ProponentApplicationRepository.findStandardCondition", query = "select cle.proposal_no as proposalNo,clsc.id as conditionId,condition_type as conditionType,condition_discription  as conditiondescription\r\n"
                + "from ua.clearance_letter_entity cle\r\n"
                + "join ua.clearance_letter_standard_condition clsc on cle.id=clsc.standard_condition_collection_ids\r\n"
                + " join master.proponent_applications pa\r\n" + "on cle.proposal_no=pa.proposal_no\r\n"
                + "and pa.id=?\r\n"
                + "group by cle.proposal_no,condition_type,clsc.id,condition_discription;", resultSetMapping = "Mapping.CertificateConditionDTO")

})

@SqlResultSetMappings({@SqlResultSetMapping(name = "Mapping.CertificateConditionDTO", classes = {
        @ConstructorResult(targetClass = CertificateConditionDTO.class, columns = {
                @ColumnResult(name = "proposalNo", type = String.class),
                @ColumnResult(name = "conditionId", type = String.class),
                @ColumnResult(name = "conditionType", type = String.class),
                @ColumnResult(name = "conditiondescription", type = String.class)})})})

@Data
@Entity
@Audited
@Table(name = "proponent_applications", schema = "master")

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProponentApplications extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer caf_id;

    @Column(nullable = false)
    private Integer proposal_id;

    @Column(name = "clearance_id", nullable = true, updatable = false, insertable = false)
    private Integer clearance_id;

    @Column(name = "moefcc_file_number", nullable = true, length = 80)
    private String moefccFileNumber;

    @Column(name = "state_file_no", nullable = true)
    private String stateFileNumber;

    @Column(name = "iro_file_no", nullable = true)
    private String iroFileNumber;

    @Column(nullable = false, unique = true, length = 80)
    private String proposal_no;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "state_id", nullable = true)
    private Integer state_id;

    @Column(name = "proposal_sequence")
    @JsonIgnore
    private Integer proposal_sequence;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "clearance_id")
    private Applications applications;

    @Column(name = "ip_address", nullable = true)
    private String ip_address;

    @NotAudited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = true)
    @JsonIgnore
    private ProjectDetails projectDetails;

    @Transient
    private ProjectDetailDto projectDetailDto;

    /*
     * @Enumerated(EnumType.ORDINAL)
     *
     * @Column(name = "last_status", nullable = false) private
     * AppConstant.Caf_Status last_status;
     */

    @Column(name = "last_status", nullable = true, length = 50)
    private String last_status;

    @Column(name = "last_visible_status", nullable = true)
    private String last_visible_status;

    @Column(nullable = true, length = 8000)
    private String last_remarks;

    @Column(nullable = true, length = 100)
    private String current_step;

    @Column(nullable = true)
    private Boolean migration_status;

    @Column(nullable = true)
    private Boolean is_sector_changed;
    @NotAudited
    @OneToOne
    @JoinColumn(name = "sector_id", nullable = true)
    private SectorEntity sectorEntity;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "office_entity_id", nullable = true)
    private OfficeEntity officeEntity;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "work_group_entity_id", nullable = true)
    private WorkGroupEntity workGroupEntity;

    @NotAudited
    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Transient
    private UpdatedUser updatedByUser;

    @Column(nullable = true)
    private String other_property;

    @Column(nullable = true)
    private String transfer_proposal_no;

    @Column(nullable = true)
    private String transfer_proposal_remarks;

    @Column(nullable = true)
    private Date transfer_proposal_date;

    @Column(nullable = true)
    private Date last_submission_date;

    @Column(nullable = true)
    private String certificate_url;

    @Column(nullable = true)
    private String certificate_url1;

    @Column(nullable = true)
    private Date grant_date1;

    @Column(nullable = true)
    private Integer proposal_duration;

    @Column(nullable = true)
    private Boolean gs_status;

    @Column(nullable = true)
    private Date grant_date;

    @Column(name = "is_legacy_proposal", nullable = true)
    private Boolean is_legacy_proposal = false;
    
    @Column(nullable = true)
    private String legacy_proposal_type;

    @Column(name = "payment_status", nullable = true)
    private String Payment_status ;


    public String getMoefccFileNumber() {
        return moefccFileNumber;
    }

    public void setMoefccFileNumber(String moefccFileNumber) {
        this.moefccFileNumber = moefccFileNumber;
    }

    public String getStateFileNumber() {
        return stateFileNumber;
    }

    public void setStateFileNumber(String stateFileNumber) {
        this.stateFileNumber = stateFileNumber;
    }

    public String getIroFileNumber() {
        return iroFileNumber;
    }

    public void setIroFileNumber(String iroFileNumber) {
        this.iroFileNumber = iroFileNumber;
    }

    public String getOther_property() {
        return other_property;
    }

    public void setOther_property(String other_property) {
        this.other_property = other_property;
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

    public Date getLast_submission_date() {
        return last_submission_date;
    }

    public void setLast_submission_date(Date last_submission_date) {
        this.last_submission_date = last_submission_date;
    }

    public String getCertificate_url() {
        return certificate_url;
    }

    public void setCertificate_url(String certificate_url) {
        this.certificate_url = certificate_url;
    }

    public Boolean getGs_status() {
        return gs_status;
    }

    public void setGs_status(Boolean gs_status) {
        this.gs_status = gs_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaf_id() {
        return caf_id;
    }

    public void setCaf_id(Integer caf_id) {
        this.caf_id = caf_id;
    }

    public Integer getProposal_id() {
        return proposal_id;
    }

    public void setProposal_id(Integer proposal_id) {
        this.proposal_id = proposal_id;
    }

    public String getProposal_no() {
        return proposal_no;
    }

    public void setProposal_no(String proposal_no) {
        this.proposal_no = proposal_no;
    }

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public String getLast_remarks() {
        return last_remarks;
    }

    public void setLast_remarks(String last_remarks) {
        this.last_remarks = last_remarks;
    }

    public Integer getProposal_sequence() {
        return proposal_sequence;
    }

    public void setProposal_sequence(Integer proposal_sequence) {
        this.proposal_sequence = proposal_sequence;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getMigration_status() {
        return migration_status;
    }

    public void setMigration_status(Boolean migration_status) {
        this.migration_status = migration_status;
    }

    public Integer getClearance_id() {
        return clearance_id;
    }

    public void setClearance_id(Integer clearance_id) {
        this.clearance_id = clearance_id;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public ProjectDetailDto getProjectDetailDto() {
        return projectDetailDto;
    }

    public void setProjectDetailDto(ProjectDetailDto projectDetailDto) {
        this.projectDetailDto = projectDetailDto;
    }

    public String getCurrent_step() {
        return current_step;
    }

    public void setCurrent_step(String current_step) {
        this.current_step = current_step;
    }

    public SectorEntity getSectorEntity() {
        return sectorEntity;
    }

    public void setSectorEntity(SectorEntity sectorEntity) {
        this.sectorEntity = sectorEntity;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public OfficeEntity getOfficeEntity() {
        return officeEntity;
    }

    public void setOfficeEntity(OfficeEntity officeEntity) {
        this.officeEntity = officeEntity;
    }

    public WorkGroupEntity getWorkGroupEntity() {
        return workGroupEntity;
    }

    public void setWorkGroupEntity(WorkGroupEntity workGroupEntity) {
        this.workGroupEntity = workGroupEntity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UpdatedUser getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(UpdatedUser updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public ProponentApplications() {
        this.migration_status = false;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getLast_status() {
        return last_status;
    }

    public void setLast_status(String last_status) {
        this.last_status = last_status;
    }

    public ProponentApplications(Integer id, Integer caf_id, Integer proposal_id, Integer clearance_id,
                                 String proposal_no, String state, Integer state_id, Applications applications, String ip_address,
                                 String last_status, String last_remarks, String current_step, Boolean migration_status,
                                 String transfer_proposal_no, String transfer_proposal_remarks, Date transfer_proposal_date,
                                 Date last_submission_date, Integer updated_by, String moefccFileNumber, String stateFileNumber, String iroFileNumber, Date grant_date,Date grant_date1,Integer proposal_duration) {
        this.id = id;
        this.caf_id = caf_id;
        this.proposal_id = proposal_id;
        this.clearance_id = clearance_id;
        this.proposal_no = proposal_no;
        this.state = state;
        this.state_id = state_id;
        this.applications = applications;
        this.ip_address = ip_address;
        this.last_status = last_status;
        this.last_remarks = last_remarks;
        this.current_step = current_step;
        this.migration_status = migration_status;
        this.transfer_proposal_no = transfer_proposal_no;
        this.transfer_proposal_remarks = transfer_proposal_remarks;
        this.transfer_proposal_date = transfer_proposal_date;
        this.last_submission_date = last_submission_date;
        this.updated_by = updated_by;
        this.moefccFileNumber = moefccFileNumber;
        this.stateFileNumber = stateFileNumber;
        this.iroFileNumber = iroFileNumber;
        this.grant_date = grant_date;
        this.grant_date1 = grant_date1;
        this.proposal_duration=proposal_duration;
    }

}
