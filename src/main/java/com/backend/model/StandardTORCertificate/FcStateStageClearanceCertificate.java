package com.backend.model.StandardTORCertificate;


import com.backend.audit.Auditable;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(name = "fc_state_stage_clearance_certificate", schema = "master")
public class FcStateStageClearanceCertificate extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "proposal_no")
    private String proposal_No;

    @Column(name = "clearance_letter_id")
    private String clearance_letter_id;

    @Column(name = "fc_Conditions")
    private String fc_Conditions;

    @Column(name = "clearance_id")
    private String clearance_id;

    @Column(name = "date", length = 100, nullable = true)
    private String date;

    @Column(name = "stateGovernmentOfficeName", length = 1500, nullable = true)
    private String stateGovernmentOfficeName;

    @Column(name = "stateGovernmentOfficeAddress", length = 1500, nullable = true)
    private String stateGovernmentOfficeAddress;

    @Column(name = "fileNo", nullable = true)
    private String fileNo;

    @Column(name = "standard_para_detail", columnDefinition = "text", nullable = true)
    private String standardPara;

    @Column(name = "generalConditions", columnDefinition = "text", nullable = true)
    private String generalConditions;

    @Column(name = "specificConditions", columnDefinition = "text", nullable = true)
    private String specificConditions;

    @Column(name = "standardConditions", columnDefinition = "text", nullable = true)
    private String standardConditions;

    @Column(name = "status", length = 100, nullable = true)
    private String status;

    @Column(name = "torDate", length = 100, nullable = true)
    private String torDate;

    @Column(name = "projectActivityId", length = 100, nullable = true)
    private String projectActivityId;

    @Column(name = "folder_dir", length = 100000, nullable = true)
    private String folderDir;

    @Column(name = "standardSubject", length = 1500, nullable = true)
    private String standardSubject;

    @Column(name = "standardSir", length = 1500, nullable = true)
    private String standardSir;

    @Column(name = "version")
    private Integer version;

    @Column(name = "pdfFilePath", length = 100000, nullable = true)
    private String filePath;

    @Column(name = "bar_code_url", length = 100000, nullable = true)
    private String barCodeUrl;

    @Column(name = "caf_id", nullable = true)
    private Integer cafId;

    @Column(name = "division_id", nullable = true)
    private Integer division_id;

    @Column(name = "proponent_id", nullable = true)
    private Integer proponentId;

    @Column(name = "project_id", nullable = true)
    private Integer project_id;

    @Column(name = "project_name", nullable = true)
    private String project_name;

    @Column(name = "ec_id", nullable = true)
    private Integer ecId;

    @Column(name = "sw_no")
    private String sw_No;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "state_Id", nullable = true)
    private Integer stateId;

    @Column(name = "proposal_id", nullable = true)
    private Integer proposal_id;

    @Column(name = "project_category", nullable = true)
    private String projectCategory;

    @Column(name = "applicationId", length = 1500, nullable = true)
    private String applicationId;

    @Column(name = "recommended_area", length = 1500, nullable = true)
    private Double  recommendedArea;
    
    @Column (name = "copy_to", columnDefinition = "text", nullable = true )
    private String copyTo;

    /************New Changes*************/
    
    @Column(name = "state", nullable = true)
    private String state;
    
    @Column(name = "district", nullable = true)
    private String district;
    
    @Column(name = "organization_name", nullable = true)
    private String organizationName;
    
    @Column(name = "form_type", nullable = true)
    private String formType;
    
    @Column(name = "division", nullable = true)
    private String division;
    
    @Column(name = "threshold", nullable = true)
    private Double threshold;

    @Column(name="to_text_value",nullable = true,length = 2000)
    private String toTextValue;

    @Column(name="yours_faithfully",columnDefinition = "text",nullable = true)
    private String yoursFaithfully;

    @Column(name="additional_email",columnDefinition = "text",nullable = true)
    private String additionalEmail;
    /************New Changes End*************/
    
    @Transient
    @Length(max=10000000)
    String htmlContent;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

}
