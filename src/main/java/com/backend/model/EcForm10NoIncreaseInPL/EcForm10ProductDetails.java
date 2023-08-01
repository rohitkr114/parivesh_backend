package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * It consists of fields from point 1.2.3.1.7 to 1.2.3.1.10
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_product_details", schema = "master")
public class EcForm10ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form10_project_details_id", nullable = true)
    @JsonIgnore
    private EcForm10ProjectDetails ecForm10ProjectDetails;
    */
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form10_basic_information_id", nullable = true)
    @JsonIgnore
    private EcForm10BasicInformation ecForm10BasicInformation;

    @OneToMany(targetEntity = EcForm10DetailsOfProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_details_of_product_id", referencedColumnName = "id")
    private Set<EcForm10DetailsOfProduct> productDetailsControls = new HashSet<>();

    @OneToMany(targetEntity = EcForm10DetailsOfRawMaterial.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_details_of_raw_material_id", referencedColumnName = "id")
    private Set<EcForm10DetailsOfRawMaterial> waterConsumptionControls = new HashSet<>();

    @Column(name = "approval_for_additional_water_consumption", nullable = true)
    private Boolean is_additional_water;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_approval_from_the_component_authority", nullable = true)
    private DocumentDetails upload_approval;

    @OneToMany(targetEntity = EcForm10DetailsOfEffluentGenerationQuantity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_details_of_effluent_generation_quantity_id", referencedColumnName = "id")
    private Set<EcForm10DetailsOfEffluentGenerationQuantity> productDetailControlQuantity = new HashSet<>();

    @OneToMany(targetEntity = EcForm10DetailsOfEffluentGenerationQuality.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_details_of_effluent_generation_quality_id", referencedColumnName = "id")
    private Set<EcForm10DetailsOfEffluentGenerationQuality> productQualityControlQuality = new HashSet<>();

    @OneToMany(targetEntity = EcForm10DetailsOfEffluentGenerationLoadInRespect.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_details_of_effluent_generation_load_in_respect_id", referencedColumnName = "id")
    private Set<EcForm10DetailsOfEffluentGenerationLoadInRespect> productDetailControlLoad = new HashSet<>();

    @Column(name = "is_segregation_of_concentrated_stream", nullable = true)
    private String segregation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_segregation_of_concentrated_stream", nullable = true)
    private DocumentDetails breif_report;

    @Column(name = "is_reduction_of_effluent_proposed", nullable = true)
    private Boolean is_reduction;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_reduction_of_effluent_proposed", nullable = true)
    private DocumentDetails report_recycle;

    @Column(name = "additional_effluent_treatment_provided", nullable = true)
    private Boolean effluent_treatment;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_additional_effluent_treatment_provided", nullable = true)
    private DocumentDetails effluent_upload;

    @Column(name = "proposal_for_upgradation_of_etp", nullable = true)
    private String upgradation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_proposal_for_upgradation_of_etp", nullable = true)
    private DocumentDetails upgradation_upload;

    @Column(name = "membership_of_common_effluent_conveyance", nullable = true)
    private Boolean is_membership;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_membership_of_common_effluent_conveyance", nullable = true)
    private DocumentDetails membership_upload;

    @Column(name = "proposed_to_achieve_zero_discharge", nullable = true)
    private Boolean is_discharge;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_proposed_to_achieve_zero_discharge", nullable = true)
    private DocumentDetails discharge_upload;

    @Column(name = "Project_has_membership_of_cetp", nullable = true)
    private Boolean is_cetp;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_Project_has_membership_of_cetp", nullable = true)
    private DocumentDetails cetp_upload;
}
