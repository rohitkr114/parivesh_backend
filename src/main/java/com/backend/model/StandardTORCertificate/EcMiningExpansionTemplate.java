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
@Table(name = "ec_mining_expansion_template", schema = "master")
public class EcMiningExpansionTemplate extends Auditable<Integer> {

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

    @OneToMany(targetEntity = EcMiningExpansionTemplateDetailOfMineralProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_mining_expansion_template_detail_of_mineral_product_id", referencedColumnName = "id")
    private Set<EcMiningExpansionTemplateDetailOfMineralProduct> detailsOfMineralsProduct = new HashSet<>();

    @Column(name = "details_project", length = 1000, nullable = true)
    private String details_project;

    @Column(name = "latitude_longitude_of__project", length = 1000, nullable = true)
    private String latitude_longitude_of__project;

    @Column(name = "non_forest_land_Existing ", length = 1000, nullable = true)
    private String non_forest_land_Existing;

    @Column(name = "non_forest_land_Proposed", length = 1000, nullable = true)
    private String non_forest_land_Proposed;

    @Column(name = "non_forest_land_Expansion", length = 1000, nullable = true)
    private String non_forest_land_Expansion;

    @Column(name = "forest_land_Existing", length = 1000, nullable = true)
    private String forest_land_Existing;

    @Column(name = "forest_land_Proposed ", length = 1000, nullable = true)
    private String  forest_land_Proposed;

    @Column(name = "forest_land_Expansion", length = 1000, nullable = true)
    private String forest_land_Expansion;

    @Column(name = "total_land_Existing", length = 1000, nullable = true)
    private String total_land_Existing;

    @Column(name = "total_land_Proposed", length = 1000, nullable = true)
    private String total_land_Proposed;

    @Column(name = "total_land_Expansion", length = 1000, nullable = true)
    private String total_land_Expansion;

    @Column(name = "lease_Area", length = 1000, nullable = true)
    private String lease_Area;

    @Column(name = "total_excavation_in_MTPA", length = 1000, nullable = true)
    private Double totalExcavationMtpa;

    @Column(name = "total_excavation_in_MCUm", length = 1000, nullable = true)
    private Double totalExcavationAnnum;

    @Column(name = "stripping_Ratio", length = 1000, nullable = true)
    private String strippingRatio;

    @Column(name = "other_information_Tax_Excavation", length = 1000, nullable = true)
    private String other_information_Tax_Excavation;

    @Column(name = "details_of_Letter_of_Intent", length = 1000, nullable = true)
    private String details_of_Letter_of_Intent;

    @Column(name = "mine_lease_validity", length = 1000, nullable = true)
    private String mine_lease_validity;

    @Column(name = "life_of_Mine", length = 1000, nullable = true)
    private String life_of_Mine;

    @Column(name = "excavationOtherInfo", length = 1000, nullable = true)
    private String excavationOtherInfo;

    @Column(name = "capacity_of_beneficiation", length = 1000, nullable = true)
    private String capacity_of_beneficiation;

    @Column(name = "proposed_process", length = 1000, nullable = true)
    private String proposed_process;

    @Column(name = "beneficiation_washing_Technology", length = 1000, nullable = true)
    private String beneficiation_washing_Technology;

    @Column(name = "other_information_Proposed_Beneficiation", length = 1000, nullable = true)
    private String other_information_Proposed_Beneficiation;

    @Column(name = "numberOfCrusher", length = 1000, nullable = true)
    private Integer numberOfCrusher;

    @Column(name = "Capacity_of_crushers", length = 1000, nullable = true)
    private Double capacityOfCrusher;

    @Column(name = "total_Capacity_of_crushers", length = 1000, nullable = true)
    private Double totalCapacityOfCrusher;

    @Column(name = "date_of_Public_Consultation", length = 1000, nullable = true)
    private String date_of_Public_Consultation;

    @Column(name = "rehabilitation_resettlement", length = 1000, nullable = true)
    private String rnrInvolved;

    @Column(name = "project_cost", length = 1000, nullable = true)
    private String projectCost;

    @Column(name = "emp_cost", length = 1000, nullable = true)
    private String empCost;

    @Column(name = "employment_details", length = 1000, nullable = true)
    private String employmentDetails;

    @Column(name = "eac", length = 1000, nullable = true)
    private String eac;


    @OneToMany(targetEntity = EcMiningExpansionTemplateStandardEcConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_mining_expansion_template_standard_ec_conditions", referencedColumnName = "id")
    private Set<EcMiningExpansionTemplateStandardEcConditions> standardEcConditionArray = new HashSet<>();

    @OneToMany(targetEntity = EcMiningExpansionTemplateSpecificEcConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_mining_expansion_template_specific_ec_conditions", referencedColumnName = "id")
    private Set<EcMiningExpansionTemplateSpecificEcConditions> specificEcConditionArray = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "additional_document_id", nullable = true)
    private DocumentDetails additional_document;

    private Boolean is_details_project_table_deleted = false;

    private Boolean is_details_product_table_deleted = false;

}
