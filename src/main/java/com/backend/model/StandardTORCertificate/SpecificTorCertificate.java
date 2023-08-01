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
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "specific_tor_certificate", schema = "master")
public class SpecificTorCertificate extends Auditable<Integer> {


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
    private String copy_to;

    @Transient
    @Length(max=10000000)
    String htmlContent;

    @Lob
    @Column(name="additional_term",nullable = true)
    private String additionalTerm;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(name = "torIdentificationNumber", length = 100, nullable = true)
    private String torIdentificationNumber;

    @Column(name = "applicabilityOfGeneralConditions", length = 1000, nullable = true)
    private String applicabilityOfGeneralConditions;

    @Column(name = "applicabilityOfSpecificConditions", length = 1000, nullable = true)
    private String applicabilityOfSpecificConditions;

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
    private String  issuingAuthority;

    @Column(name = "locationOfProject", length = 1000, nullable = true)
    private String locationOfProject;

    @Column(name = "nameOfOrganization", length = 1000, nullable = true)
    private String nameOfOrganization;

    @Column(name = "nameOfProject", length = 1000, nullable = true)
    private String nameOfProject;

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

    @Column(name = "generalConditions", columnDefinition = "text", nullable = true)
    private String generalConditions;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "additional_document_id", nullable = true)
    private DocumentDetails additional_document;

    private Boolean is_details_project_table_deleted = false;

    private Boolean is_details_product_table_deleted = false;
    @Column(name = "sub_activity_condition_applicability", nullable = true)
    private Boolean sub_activity_condition_applicability;

    @OneToMany(targetEntity = SpecificConditions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "specific_conditions_id", referencedColumnName = "id")
    private List<SpecificConditions> specificConditionArray;

    @OneToMany(targetEntity = SpecificTorCertificatePlantEquipment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "specific_tor_certificate_plant_equipment_id", referencedColumnName = "id")
    private List<SpecificTorCertificatePlantEquipment> plantEquipmentsSpecific = new ArrayList<>();

    @OneToMany(targetEntity = SpecificTorCertificateDetailOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "specific_tor_certificate_detail_of_product_id", referencedColumnName = "id")
    private List<SpecificTorCertificateDetailOfProduct> detailsOfProductsSpecific = new ArrayList<>();

}
