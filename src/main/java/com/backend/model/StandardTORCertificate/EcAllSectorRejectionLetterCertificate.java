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
@Table(name = "ec_all_sector_rejection_letter_certificate", schema = "master")
public class EcAllSectorRejectionLetterCertificate extends Auditable<Integer> {

    @Transient
    @Length(max = 10000000)
    String htmlContent;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "proposal_no")
    private String proposal_No;
    @Column(name = "caf_id", nullable = false)
    private Integer cafId;
    @Column(name = "proponent_id", nullable = false)
    private Integer proponentId;
    @Column(name = "ec_id", nullable = false)
    private Integer ecId;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "version")
    private Integer version;
    @Column(name = "pdfFilePath", length = 100000, nullable = true)
    private String filePath;
    @Column(name = "bar_code_url", length = 100000, nullable = true)
    private String barCodeUrl;
    @Column(name = "copy_to", columnDefinition = "text", nullable = true)
    private String copyTo;
    @Column(name = "status", length = 100, nullable = true)
    private String status;
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
    private String issuingAuthority;

    @Column(name = "ecDate", length = 1000, nullable = true)
    private String ecDate;

    @Column(name = "statusOfImplementationOfProject", length = 1000, nullable = true)
    private String statusOfImplementationOfProject;

    @Column(name = "applicabilityOfGeneralConditions", length = 1000, nullable = true)
    private String applicabilityOfGeneralConditions;

    @Column(name = "validityOfEC", length = 1000, nullable = true)
    private String validityOfEC;

    @Column(name = "standardPara", columnDefinition = "text", nullable = true)
    private String standardPara;

    @OneToMany(targetEntity = EcAllSectorRejectionLetterCertificatePlantEquipments.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_all_sector_rejection_letter_certificate_plant_equipments_id", referencedColumnName = "id")
    private Set<EcAllSectorRejectionLetterCertificatePlantEquipments> plantEquipmentArray = new HashSet<>();

    @OneToMany(targetEntity = EcAllSectorRejectionLetterCertificateDetailOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_all_sector_rejection_letter_certificate_detail_of_product_id", referencedColumnName = "id")
    private Set<EcAllSectorRejectionLetterCertificateDetailOfProduct> detailsOfProducts = new HashSet<>();

    @Column(name = "details_project", length = 1000, nullable = true)
    private String details_project;

    @Column(name = "latitude_longitude_project", length = 1000, nullable = true)
    private String latitude_longitude__project;

    @Column(name = "details_of_previous_EC_Rejection ", length = 1000, nullable = true)
    private String details_of_previous_EC_Rejection;

    @Column(name = "terms_of_Reference_for_undertaking", length = 1000, nullable = true)
    private String terms_of_Reference_for_undertaking;

    @Column(name = "non_Forest_land", length = 1000, nullable = true)
    private String totalNonForestLand;

    @Column(name = "forest_land", length = 1000, nullable = true)
    private Double forestLandB;

    @Column(name = "total_land ", length = 1000, nullable = true)
    private String totalLand;

    @Column(name = "land_acquisition_details", length = 1000, nullable = true)
    private String land_acquisition_details;

    @Column(name = "period_of_baseline_data", length = 1000, nullable = true)
    private String period_of_baseline_data;

    @Column(name = "date_consultation", length = 1000, nullable = true)
    private String date_consultation;

    @Column(name = "action_Plan", length = 1000, nullable = true)
    private String action_Plan;

    @Column(name = "no_of_Trees_to_be_cut", length = 1000, nullable = true)
    private String no_of_Trees_to_be_cut;

    @Column(name = "area_covered_with_plantation", length = 1000, nullable = true)
    private String area_covered_with_plantation;

    @Column(name = "no_of_Trees_proposed_for_transplantation", length = 1000, nullable = true)
    private String no_of_Trees_proposed_for_transplantation;

    @Column(name = "green_Belt", length = 1000, nullable = true)
    private String green_Belt;

    @Column(name = "water_Body_Exist", length = 1000, nullable = true)
    private String waterBody;

    @Column(name = "details_of_the_Ground_Water_table", length = 1000, nullable = true)
    private String details_of_the_Ground_Water_table;

    @Column(name = "category_safe_exploited", length = 1000, nullable = true)
    private String category_safe_exploited;

    @Column(name = "measures_to_recharge_ground_water", length = 1000, nullable = true)
    private String measures_to_recharge_ground_water;

    @Column(name = "water_requirement", length = 1000, nullable = true)
    private String waterRequirement;

    @Column(name = "details_of_recycle_of_wastewater", length = 1000, nullable = true)
    private String details_of_recycle_of_wastewater;

    @Column(name = "details_of_proposed_common_off_site", length = 1000, nullable = true)
    private String details_of_proposed_common_off_site;

    @Column(name = "details_of_proposed_setup_on_Site_Sewage_Treatment_Plant", length = 1000, nullable = true)
    private String details_of_proposed_setup_on_Site_Sewage_Treatment_Plant;

    @Column(name = "source_Wise_Probable_Air_Pollutants", length = 1000, nullable = true)
    private String source_Wise_Probable_Air_Pollutants;

    @Column(name = "rehabilitation_Resettlement", length = 1000, nullable = true)
    private String rnrInvolved;

    @Column(name = "project_Cost", length = 1000, nullable = true)
    private String projectCost;

    @Column(name = "emp_Cost", length = 1000, nullable = true)
    private String empCost;

    @Column(name = "employment_Details", length = 1000, nullable = true)
    private String employmentDetails;

    @Column(name = "benefits_of_the_Project", length = 1000, nullable = true)
    private String benefits_of_the_Project;

    @Column(name = "folder_dir", length = 100000, nullable = true)
    private String folderDir;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "additional_document_id", nullable = true)
    private DocumentDetails additional_document;

    private Boolean is_details_project_table_deleted = false;

    private Boolean is_details_product_table_deleted = false;

}

