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
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_amendment_certificate", schema = "master")
public class EcAmendmentCertificate extends Auditable<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "proposal_no")
	private String proposal_No;

	@Column(name = "caf_id", nullable = false)
	private Integer cafId;

	@Column(name = "proponent_id", nullable = false)
	private Integer proponentId;

    @Column(name = "ec_id", nullable = true)
    private Integer ecId;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "version")
	private Integer version;

    @Column(name = "pdfFilePath", length = 100000, nullable = true)
    private String filePath;

	@Column(name = "folder_dir", length = 100000, nullable = true)
	private String folderDir;

    @Column(name = "bar_code_url", length = 100000, nullable = true)
    private String barCodeUrl;
    
    @Column (name = "copy_to", columnDefinition = "text", nullable = true )
    private String copy_to;

	@Column(name = "status", length = 100, nullable = true)
	private String status;

	@Transient
	@Length(max = 10000000)
	String htmlContent;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@Column(name = "torIdentificationNumber", length = 100, nullable = true)
	private String torIdentificationNumber;

	@Column(name = "applicabilityOfGeneralConditions", length = 1000, nullable = true)
	private String applicabilityOfGeneralConditions;

    @Column(name = "category", length = 1000, nullable = true)
    private String category;
    
    @Column(name = "sector", length = 1000, nullable = true)
    private String sector;

	@Column(name = "clearenceType", length = 1500, nullable = true)
	private String clearanceType;

	@Column(name = "companyname", length = 1000, nullable = true)
	private String companyname;

	@Column(name = "date", length = 1000, nullable = true)
	private String date;

	@Column(name = "fileNo", length = 100, nullable = true)
	private String fileNo;

	@Column(name = "issuingAuthority", length = 1000, nullable = true)
	private String issuingAuthority;

	@Column(name = "locationOfProject", length = 1000, nullable = true)
	private String locationOfProject;

	@Column(name = "nameOfOrganization", length = 1000, nullable = true)
	private String nameOfOrganization;

	@Column(name = "nameOfProject", length = 1000, nullable = true)
	private String nameOfProject;

	@Column(name = "projectIncludedScheduleNo", length = 1000, nullable = true)
	private String projectIncludedScheduleNo;
	
	@Column(name = "additional_term",columnDefinition = "text", nullable = true)
    private String additionalTerm;

	@Column(name = "proponent", length = 1000, nullable = true)
	private String proponent;

	@Column(name = "proponentEmail", length = 1000, nullable = true)
	private String proponentEmail;

	@Column(name = "registeredAddress", length = 1000, nullable = true)
	private String registeredAddress;

	@Column(name = "statusOfImplementationOfProject", length = 1000, nullable = true)
	private String statusOfImplementationProject;

	@Column(name = "standardPara", columnDefinition = "text", nullable = true)
	private String standardPara;

	@Column(name = "standardSir", length = 1500, nullable = true)
	private String standardSir;

	@Column(name = "standardSubject", length = 1500, nullable = true)
	private String standardSubject;

    @Column(name = "torDate", length = 100, nullable = true)
    private String torDate;
    
    @Column(name = "validity_ec", length = 1000, nullable = true)
    private String ecValidity;
    
    @Column(name = "reason", length = 1000, nullable = true)
    private String reason;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "additional_document_id", nullable = true)
	private DocumentDetails additional_document;

	private Boolean is_details_project_table_deleted = false;

	private Boolean is_details_product_table_deleted = false;
	
	 private Boolean is_amendment_logs_table_deleted = false;

	@OneToMany(targetEntity = EcAmendmentCertificatePlantEquipment.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_amendment_certificate_plant_equipment_id", referencedColumnName = "id")
	private Set<EcAmendmentCertificatePlantEquipment> plantEquipmentArray = new HashSet<>();

	@OneToMany(targetEntity = EcAmendmentCertificateOtherAmendment.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_amendment_certificate_other_amendment_id", referencedColumnName = "id")
	private Set<EcAmendmentCertificateOtherAmendment> otherAmendmentArray = new HashSet<>();

	@OneToMany(targetEntity = EcAmendmentCertificateAdditionalCondition.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_amendment_certificate_additional_certificate_id", referencedColumnName = "id")
	private Set<EcAmendmentCertificateAdditionalCondition> additionalConditions = new HashSet<>();

}
