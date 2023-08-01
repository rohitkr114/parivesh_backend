package com.backend.model.EcForm10NoIncreaseInPL;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_details_of_emission_generation_from_other_sources", schema = "master")

public class EcForm10DetailsOfEmissionGenerationFromOtherSources {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "other_source", nullable = true,length = 100)
    private String point_source_other;

    @Column(name = "height_of_discharge", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float height_of_other;

    @Column(name = "emission_rate_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float ec_emission_on_rate_other;

    @Column(name = "emission_unit_as_per_ec", nullable = true,length = 100)
    private  String ec_unit_emission_rate_other;

    @Column(name = "total_emission_rate_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float ec_total_emission_other;

    @Column(name = "total_emission_unit_as_per_ec", nullable = true,length = 100)
    private  String ec_total_emission_unit_other;

    @Column(name = "emission_rate_after_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float proposed_emission_on_rate_other;

    @Column(name = "emission_rate_after_proposed_unit", nullable = true,length = 100)
    private String proposed_unit_emission_rate_other;

    @Column(name = "total_emission_after_proposed_change", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float proposed_total_emission_other;

    @Column(name = "total_emission_after_proposed_change_unit", nullable = true,length = 100)
    private String proposed_total_emission_unit_other;

}
