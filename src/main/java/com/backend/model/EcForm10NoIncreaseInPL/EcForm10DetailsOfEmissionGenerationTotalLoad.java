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
@Table(name = "ec_form10_details_of_emission_generation_total_load", schema = "master")
public class EcForm10DetailsOfEmissionGenerationTotalLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "total_load_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float total_land_of_emission;

    @Column(name = "total_load_unit_as_per_ec", nullable = true)
    private String unit_1;

    @Column(name = "total_load_in_apcm_existing", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float apcm_existing;

    @Column(name = "total_load_in_apcm_existing_unit", nullable = true,length = 50)
    private String unit_4;

    @Column(name = "total_load_in_emission", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float total_land_respect_of_raw_material;

    @Column(name = "total_load_in_emission_unit", nullable = true,length = 50)
    private String unit_2;

    @Column(name = "total_load_in_apcm_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float apcm_raw;

    @Column(name = "total_load_in_apcm_proposed_unit", nullable = true,length = 50)
    private String unit_3;

    @Column(name = "remarks", nullable = true,length = 500)
    private String remarks_if_any;

}
