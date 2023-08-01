package com.backend.model.StandardTORCertificate;

import com.backend.audit.Auditable;
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
@Table(name = "ec_expansion_nipl_certificate", schema = "master")
public class EcExpansionNIPLCertificate extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status", length = 100, nullable = true)
    private String status;

    @Column(name = "form10Id")
    private Integer form10Id;

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

    @Column(name = "date", length = 1000, nullable = true)
    private String date;

    @Column(name = "proponent", length = 1000, nullable = true)
    private String proponent;

    @Column(name = "companyname", length = 1000, nullable = true)
    private String companyname;

    @Column(name = "correspondenceAddress", length = 1000, nullable = true)
    private String correspondenceAddress;

    @Column(name = "proponentEmail", length = 1000, nullable = true)
    private String proponentEmail;

    @Column(name = "standardSubject", length = 1500, nullable = true)
    private String standardSubject;

    @Column(name = "standardSir", length = 1500, nullable = true)
    private String standardSir;

    @Column(name = "torIdentificationNumber", length = 100, nullable = true)
    private String torIdentificationNumber;

    @Column(name = "fileNo", length = 100, nullable = true)
    private String fileNo;

    @Column(name = "clearenceType", length = 1000, nullable = true)
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
    private String  issuingAuthority;

    @Column(name = "ecDate", length = 1000, nullable = true)
    private String  ecDate;

    @Column(name = "statusOfImplementationOfProject", length = 1000, nullable = true)
    private String  statusOfImplementationOfProject;

    @Column(name = "applicabilityOfGeneralConditions", length = 1000, nullable = true)
    private String applicabilityOfGeneralConditions;

    @Column(name = "validityOfExpansionEc", length = 1000, nullable = true)
    private String validityOfExpansionEc;

    @Column(name = "standardPara", columnDefinition = "text", nullable = true)
    private String standardPara;

    @Column(name = "details_project", length = 1500, nullable = true)
    private String details_project;

    @Column(name = "latitude_longitude_project", length = 1500, nullable = true)
    private String latitude_longitude_project;

    @Column(name = "non_forest_land", length = 1500, nullable = true)
    private String non_forest_land;

    @Column(name = "forest_land", length = 1500, nullable = true)
    private String forest_land;

    @Column(name = "total_land", length = 1500, nullable = true)
    private String total_land;

    @Column(name = "date_consultation", length = 1500, nullable = true)
    private String date_consultation;

    @Column(name = "rehabilitation_resettlement", length = 1500, nullable = true)
    private String rehabilitation_resettlement;

    @Column(name = "project_cost", length = 1500, nullable = true)
    private String project_cost;

    @Column(name = "emp_cost", length = 1500, nullable = true)
    private String emp_cost;

    @Column(name = "employment_details", length = 1500, nullable = true)
    private String employment_details;

    private Boolean is_details_project_table_deleted = false;

    private Boolean is_details_product_table_deleted = false;

    @OneToMany(targetEntity = EcExpansionNIPLCertificatePlantEquipment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_expansion_NIPL_certificate_plant_equipment_id", referencedColumnName = "id")
    private Set<EcExpansionNIPLCertificatePlantEquipment> plantEquipmentArray = new HashSet<>();

    @OneToMany(targetEntity = EcExpansionNIPLCertificateDetailOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_expansion_NIPL_certificate_detail_of_project_id", referencedColumnName = "id")
    private Set<EcExpansionNIPLCertificateDetailOfProduct> detailsOfProducts = new HashSet<>();

    @OneToMany(targetEntity = EcExpansionNIPLCertificateStandardEcConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_expansion_NIPL_certificate_standard_ec_conditions_id", referencedColumnName = "id")
    private Set<EcExpansionNIPLCertificateStandardEcConditions> standardEcConditionArray = new HashSet<>();

    @OneToMany(targetEntity = EcExpansionNIPLCertificateSpecificEcConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_expansion_NIPL_certificate_specific_ec_conditions_id", referencedColumnName = "id")
    private Set<EcExpansionNIPLCertificateSpecificEcConditions> specificEcConditionArray = new HashSet<>();

}
