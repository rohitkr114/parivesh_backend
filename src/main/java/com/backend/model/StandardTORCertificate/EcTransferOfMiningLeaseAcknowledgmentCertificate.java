package com.backend.model.StandardTORCertificate;


import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_transfer_of_mining_lease_acknowledgment_certificate", schema = "master")
public class EcTransferOfMiningLeaseAcknowledgmentCertificate extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status", length = 100, nullable = true)
    private String status;

    @Column(name = "torDate", length = 100, nullable = true)
    private String torDate;

    @Column(name = "caf_id", nullable = false)
    private Integer cafId;

    @Column(name = "proponent_id", nullable = false)
    private Integer proponentId;

    @Column(name = "ec_id", nullable = true)
    private Integer ecId;

    @Column(name = "proposal_no")
    private String proposal_No;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "version")
    private Integer version;

    @Column(name = "folder_dir", length = 100000, nullable = true)
    private String folderDir;

    @Column(name = "pdfFilePath", length = 100000, nullable = true)
    private String filePath;

    @Column(name = "bar_code_url", length = 100000, nullable = true)
    private String barCodeUrl;
    
    @Column (name = "copy_to", columnDefinition = "text", nullable = true )
    private String copyTo;

    @Transient
    @Length(max=10000000)
    String htmlContent;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(name = "projectId")
    private Integer projectID;


    @Column(name = "nameOfProject", length = 1000, nullable = true)
    private String nameOfProject;

    @Column(name = "locationOfProject", length = 1000, nullable = true)
    private String locationOfProject;

    @Column(name = "category", length = 1000, nullable = true)
    private String category;
    
	@Column(name = "sector", length = 1000, nullable = true)
    private String sector;

    @Column(name = "projectIncludedScheduleNo", length = 1000, nullable = true)
    private String projectIncludedScheduleNo;

    @Column(name = "clearenceType", length = 1500, nullable = true)
    private String clearanceType;

    @Column(name = "nameOfPreviousLessee", length = 1500, nullable = true)
    private String nameOfPreviousLessee;

    @Column(name = "nameOfSuccessfulBidder", length = 1500, nullable = true)
    private String nameOfSuccessfulBidder;

    @Column(name = "dateOfValidity", length = 1500, nullable = true)
    private String dateOfValidity;

    @Column(name = "standard_para_detail", columnDefinition = "text", nullable = true)
    private String standardPara;

    @Column(name = "date", length = 1000, nullable = true)
    private String date;
    
    @Column(name = "fileNo", length = 100, nullable = true)
    private String fileNo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "additional_document_id", nullable = true)
    private DocumentDetails additional_document;
}
