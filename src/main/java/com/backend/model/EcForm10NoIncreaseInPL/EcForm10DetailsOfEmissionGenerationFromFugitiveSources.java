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
@Table(name = "ec_form10_details_of_emission_generation_from_fugitive_sources", schema = "master")
public class EcForm10DetailsOfEmissionGenerationFromFugitiveSources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fugitive_sources", nullable = true,length = 100)
    private String point_source_fugitive;

    @Column(name = "height_of_discharge", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float height_of_fugitive;

    @Column(name = "emission_rate_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float ec_emission_on_rate_fugitive;

    @Column(name = "emission_unit_as_per_ec", nullable = true,length = 50)
    private  String ec_unit_emission_rate_fugitive;

    @Column(name = "total_emission_rate_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float ec_total_emission_fugitive;

    @Column(name = "total_emission_unit_as_per_ec", nullable = true,length = 50)
    private  String ec_total_emission_unit_fugitive;

    @Column(name = "emission_rate_after_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float proposed_emission_on_rate_fugitive;

    @Column(name = "emission_unit_after_proposed", nullable = true,length = 50)
    private String proposed_unit_emission_rate_fugitive;

    @Column(name = "total_emission_rate_after_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float proposed_total_emission_fugitive;

    @Column(name = "total_emission_unit_after_proposed", nullable = true,length = 50)
    private String proposed_total_emission_unit_fugitive;

}
