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
 * It consists of fields from point 1.2.3.1.11 to 1.2.3.1.14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_hazardous_waste_generation", schema = "master")
public class EcForm10HazardousWasteGeneration {

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

    @OneToMany(targetEntity = EcForm10HazardousWasteGenerationQuantity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_hazardous_waste_generation_quantity_id", referencedColumnName = "id")
    private Set<EcForm10HazardousWasteGenerationQuantity> quantity_and_waste = new HashSet<>();

    @Column(name = "violation_carried_out", nullable = true)
    private Boolean violation_carried_out;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "working_plan_prescription_copy", nullable = true)
    private DocumentDetails report_on_proposal;

    @Column(name = "membership_of_common_secure_landfill", nullable = true)
    private Boolean membership_of_common_secure_landfill;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_membership", nullable = true)
    private DocumentDetails report_on_membership;

    @Column(name = "membership_of_common_hazardous_waste", nullable = true)
    private Boolean membership_of_common_hazardous_waste;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_of_membership_of_common_hazardous_waste", nullable = true)
    private DocumentDetails report_on_membership_of_common_hazardous_waste;

    @Column(name = "authorized_environmental_auditor", nullable = true)
    private String authorized_environmental_auditor;

    @Column(name = "authorized_environmental_auditor_other", nullable = true)
    private String authorized_environmental_auditor_other;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "certificate_of_no_inc_in_pollution", nullable = true)
    private DocumentDetails certificate_of_no_inc_in_pollution;

    @OneToMany(targetEntity = EcForm10HazardousWasteGenerationMonitoringSystem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_hazardous_waste_generation_monitoring_system_id", referencedColumnName = "id")
    private Set<EcForm10HazardousWasteGenerationMonitoringSystem> monitoring = new HashSet<>();

}

