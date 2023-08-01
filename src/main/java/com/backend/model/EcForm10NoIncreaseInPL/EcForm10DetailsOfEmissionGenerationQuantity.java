package com.backend.model.EcForm10NoIncreaseInPL;


import com.backend.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_details_of_emission_generation_quantity", schema = "master")

public class EcForm10DetailsOfEmissionGenerationQuantity extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "point_source", nullable = true,length = 100)
    private String point_source;

    @Column(name = "height_of_stack", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float height_of_stack;

    @Column(name = "emission_rate_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float ec_emission_on_rate;

    @Column(name = "emission_unit_as_per_ec", nullable = true,length = 50)
    private String ec_unit_emission_rate;

    @Column(name = "total_emission_rate_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float ec_total_emission;

    @Column(name = "total_emission_unit_as_per_ec", nullable = true,length = 50)
    private String ec_total_emission_unit;

    @Column(name = "emission_rate_after_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float proposed_emission_on_rate;

    @Column(name = "emission_rate_after_proposed_unit", nullable = true,length = 50)
    private String proposed_unit_emission_rate;

    @Column(name = "total_emission_rate_after_proposed", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float proposed_total_emission;

    @Column(name = "total_emission_rate_after_proposed_unit", nullable = true,length = 50 )
    private String proposed_total_emission_unit;












}
