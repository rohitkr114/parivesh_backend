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
@Table(name = "ec_fresh_letter_template_certificate", schema = "master")
public class EcFreshLetterTemplateCertificate extends Auditable<Integer> {

    @Transient
    @Length(max = 10000000)
    String htmlContent;
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
    @Column(name = "copy_to", columnDefinition = "text", nullable = true)
    private String copy_to;
    @Column(name = "status", length = 100, nullable = true)
    private String status;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(name = "applicabilityOfGeneralConditions", length = 1000, nullable = true)
    private String applicabilityOfGeneralConditions;

    @Column(name = "category", length = 1000, nullable = true)
    private String category;

    @Column(name = "sector", length = 1000, nullable = true)
    private String sector;

    @Column(name = "clearenceType", length = 1000, nullable = true)
    private String clearanceType;

    @Column(name = "companyname", length = 1000, nullable = true)
    private String companyname;

    @Column(name = "date", length = 1000, nullable = true)
    private String date;

    @Column(name = "fileNo", length = 100, nullable = true)
    private String fileNo;

    @Column(name = "torIdentificationNumber", length = 100, nullable = true)
    private String torIdentificationNumber;

    @Column(name = "issuingAuthority", length = 1000, nullable = true)
    private String issuingAuthority;

    @Column(name = "locationOfProject", length = 1000, nullable = true)
    private String locationOfProject;

    @Column(name = "nameOfOrganization", length = 1000, nullable = true)
    private String nameOfOrganization;

    @Column(name = "nameOfProject", length = 1000, nullable = true)
    private String nameOfProject;

    @Column(name = "validity_ec", length = 1000, nullable = true)
    private String validity_ec;

    @Column(name = "standardEcConditions", columnDefinition = "text", nullable = true)
    private String standardEcConditions;

    @Column(name = "specificEcConditions", columnDefinition = "text", nullable = true)
    private String specificEcConditions;

    @Column(name = "projectIncludedScheduleNo", length = 1000, nullable = true)
    private String projectIncludedScheduleNo;

    @Column(name = "proponent", length = 1000, nullable = true)
    private String proponent;

    @Column(name = "proponentEmail", length = 1000, nullable = true)
    private String proponentEmail;

    @Column(name = "registeredAddress", length = 1000, nullable = true)
    private String registeredAddress;

    @Column(name = "standard_para_detail", columnDefinition = "text", nullable = true)
    private String standardPara;

    @Column(name = "standardSir", length = 1500, nullable = true)
    private String standardSir;

    @Column(name = "standardSubject", length = 1500, nullable = true)
    private String standardSubject;

    @Column(name = "details_project", length = 1500, nullable = true)
    private String details_project;

    @Column(name = "date_consultation", length = 1500, nullable = true)
    private String date_consultation;

    @Column(name = "rehabilitation_resettlement", length = 1500, nullable = true)
    private String rehabilitation_resettlement;

    @Column(name = "latitude_longitude_project", length = 1500, nullable = true)
    private String latitude_longitude_project;

    @Column(name = "project_cost", length = 1500, nullable = true)
    private String project_cost;

    @Column(name = "emp_cost", length = 1500, nullable = true)
    private String emp_cost;

    @Column(name = "employment_details", length = 1500, nullable = true)
    private String employment_details;

    @Column(name = "non_forest_land", length = 1500, nullable = true)
    private String non_forest_land;

    @Column(name = "forest_land", length = 1500, nullable = true)
    private String forest_land;

    @Column(name = "total_land", length = 1500, nullable = true)
    private String totalLand;

    @Column(name = "land_requirement", length = 1500, nullable = true)
    private String land_requirement;

    @Column(name = "torDate", length = 100, nullable = true)
    private String torDate;

    @Column(name = "rnrInvolved", length = 100, nullable = true)
    private String rnrInvolved;

    @Lob
    @Column(name = "additional_term", nullable = true)
    private String additionalTerm;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "additional_document_id", nullable = true)
    private DocumentDetails additional_document;
    private Boolean is_details_project_table_deleted = false;

    private Boolean is_details_product_table_deleted  = false;
    @OneToMany(targetEntity = EcFreshLetterCertificatePlantEquipment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_fresh_letter_certificate_plant_equipment_id", referencedColumnName = "id")
    private Set<EcFreshLetterCertificatePlantEquipment> ecFreshLetterCertificatePlantEquipment = new HashSet<>();

    @OneToMany(targetEntity = EcFreshLetterCertificateDetailOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_fresh_letter_certificate_detail_of_product_id", referencedColumnName = "id")
    private Set<EcFreshLetterCertificateDetailOfProduct> ecFreshLetterCertificateDetailOfProduct = new HashSet<>();

    @OneToMany(targetEntity = EcFreshLetterCertificateSpecificConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_fresh_letter_certificate_specific_condition_id", referencedColumnName = "id")
    private Set<EcFreshLetterCertificateSpecificConditions> freshLetterSpecificCondition = new HashSet<>();

    @OneToMany(targetEntity = EcFreshLetterCertificateStandardConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_fresh_letter_certificate_standard_condition_id", referencedColumnName = "id")
    private Set<EcFreshLetterCertificateStandardConditions> freshLetterStandardCondition = new HashSet<>();

}
