package com.backend.model.StandardTORCertificate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.upload.UploadModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Crz_grant_certificate", schema = "master")
public class CrzGrantCertificate extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "status", length = 100, nullable = true)
	private String status;

	@Column(name = "Date", length = 100, nullable = true)
	private String Date;

	@Column(name = "caf_id", nullable = false)
	private Integer cafId;

	@Column(name = "proponent_id", nullable = false)
	private Integer proponentId;

	@Column(name = "proposal_no")
	private String proposal_No;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "version")
	private Integer version;

	@Column(name = "pdfFilePath", length = 100000, nullable = true)
	private String filePath;

	@Column(name = "bar_code_url", length = 100000, nullable = true)
	private String barCodeUrl;

	@Column(name = "copy_to", columnDefinition = "text", nullable = true)
	private String copy_to;

	@Transient
	@Length(max = 10000000)
	String htmlContent;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "additional_document_id", nullable = true)
	private DocumentDetails additional_document;

	@Column(name = "applicabilityOfGeneralConditions", columnDefinition = "text", nullable = true)
	private String applicabilityOfGeneralConditions;

	@Column(name = "clearenceType", length = 1500, nullable = true)
	private String clearanceType;

	@Column(name = "companyname", length = 1000, nullable = true)
	private String companyname;

	@Column(name = "fileNo", length = 100, nullable = true)
	private String fileNo;

	@Column(name = "state_file_no", nullable = true)
	private String stateFileNumber;

	@Column(name = "issuingAuthority", length = 1000, nullable = true)
	private String issuingAuthority;

	@Column(name = "locationOfProject", length = 1000, nullable = true)
	private String locationOfProject;

	@Column(name = "nameOfOrganization", length = 1000, nullable = true)
	private String nameOfOrganization;

	@Column(name = "nameOfProject", length = 1000, nullable = true)
	private String nameOfProject;

//    @Column(name = "projectIncludedScheduleNo", length = 1000, nullable = true)
//    private String projectIncludedScheduleNo;

	@Column(name = "proponent", length = 1000, nullable = true)
	private String proponent;

	@Column(name = "proponentEmail", length = 1000, nullable = true)
	private String proponentEmail;

	@Column(name = "registeredAddress", length = 1000, nullable = true)
	private String registeredAddress;

	@Column(name = "additional_term", nullable = true)
	private String additionalTerm;

	@Column(name = "standard_para_detail", columnDefinition = "text", nullable = true)
	private String standardPara;

	@Column(name = "standard_para_1", columnDefinition = "text", nullable = true)
	private String standardPara1;

	@Column(name = "standard_para_2", columnDefinition = "text", nullable = true)
	private String standardPara2;

	@Column(name = "standard_para_3", columnDefinition = "text", nullable = true)
	private String standardPara3;

	@Column(name = "standard_para_4", columnDefinition = "text", nullable = true)
	private String standardPara4;

	@Column(name = "standard_para_5", columnDefinition = "text", nullable = true)
	private String standardPara5;

	@Column(name = "standardSir", columnDefinition = "text", nullable = true)
	private String standardSir;

	@Column(name = "standardSubject", columnDefinition = "text", nullable = true)
	private String standardSubject;

	// this all fields will be single string no mapping needed.
	@Column(name = "generalConditions", columnDefinition = "text", nullable = true)
	private String generalConditions;

	@Column(name = "folder_dir", length = 100000, nullable = true)
	private String folderDir;

}
