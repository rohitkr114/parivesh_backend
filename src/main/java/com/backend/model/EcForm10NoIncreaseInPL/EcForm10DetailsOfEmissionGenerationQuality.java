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
@Table(name = "ec_form10_details_of_emission_generation_quality", schema = "master")

public class EcForm10DetailsOfEmissionGenerationQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "stack_attached", nullable = true,length = 200)
    private String stack_attached;

    @Column(name = "stack_height", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float stack_height;

    @Column(name = "apcm", nullable = true,length = 200)
    private String apcm;

    @Column(name = "parameter", nullable = true,length = 100)
    private String parameter;

    @Column(name = "concentration_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float as_per_ec;

    @Column(name = "concentration_unit", nullable = true,length = 50)
    private String unit_quality;

    @Column(name = "concentration_after_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float after_proposed_raw_material;

    @Column(name = "concentration_after_proposed_unit", nullable = true,length = 50)
    private String unit_quality_1;

}
