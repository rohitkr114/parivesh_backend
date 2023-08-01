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
@Table(name = "ec_mining_extension_of_validity_template", schema = "master")
public class EcMiningExtensionofValidityTemplateCertificate extends Auditable<Integer> {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "caf_id", nullable = false)
  private Integer cafId;

  @Column(name = "proponent_id", nullable = false)
  private Integer proponentId;

  @Column(name = "ec_id", nullable = false)
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

  @Column(name = "status")
  private String status;

  @Transient
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Length(max = 10000000)
  String htmlContent;

  @Transient
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private ProponentApplications proponentApplications;

  @Column(name = "date", length = 1000, nullable = true)
  private String date;

  @Column(name = "proponent", length = 1000, nullable = true)
  private String proponent;

  @Column(name = "companyName", length = 1000, nullable = true)
  private String companyName;

  @Column(name = "correspondenceAddress", length = 1000, nullable = true)
  private String correspondenceAddress;

  @Column(name = "proponentEmail", length = 1000, nullable = true)
  private String proponentEmail;

  @Column(name = "standardSubject", length = 1500, nullable = true)
  private String standardSubject;

  @Column(name = "standardSir", length = 1500, nullable = true)
  private String standardSir;

  @Column(name = "standard_para_detail", length = 1500000, nullable = true)
  private String standardPara;

  @Column(name = "torIdentificationNumber", length = 100, nullable = true)
  private String torIdentificationNumber;

  @Column(name = "fileNo", length = 100, nullable = true)
  private String fileNo;

  @Column(name = "clearanceType", length = 1000, nullable = true)
  private String clearanceType;

  @Column(name = "category", length = 1000, nullable = true)
  private String category;
  
  @Column(name = "sector", length = 1000, nullable = true)
  private String sector;

  @Column(name = "projectIncludedScheduleNo", length = 1000, nullable = true)
  private String projectIncludedScheduleNo;

  @Column(name = "nameOfProject", length = 1000, nullable = true)
  private String nameOfProject;

  @Column(name = "nameOfOrganization", length = 1000, nullable = true)
  private String nameOfOrganization;

  @Column(name = "locationOfProject", length = 1000, nullable = true)
  private String locationOfProject;

  @Column(name = "issuingAuthority", length = 1000, nullable = true)
  private String issuingAuthority;

  @Column(name = "ecDate", length = 1000, nullable = true)
  private String ecDate;

  @Column(name = "statusOfImplementationOfProject", length = 1000, nullable = true)
  private String statusOfImplementationOfProject;

  @Column(name = "whetherAnyAmendmentToTheEarlierECHasBeenSought", length = 1000, nullable = true)
  private boolean whetherAnyAmendmentToTheEarlierECHasBeenSought;

  @Column(name = "extendedECValidityDate", length = 1000, nullable = true)
  private String  extendedECValidityDate;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "additional_document_id", nullable = true)
  private DocumentDetails additional_document;
  private Boolean is_details_project_table_deleted = false;

  private Boolean is_details_product_table_deleted = false;
}


