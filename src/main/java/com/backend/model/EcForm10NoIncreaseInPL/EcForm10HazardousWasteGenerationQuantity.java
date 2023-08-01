package com.backend.model.EcForm10NoIncreaseInPL;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_hazardous_waste_generation_quantity", schema = "master")
public class EcForm10HazardousWasteGenerationQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type_of_waste", nullable = true,length = 200)
    private String type_of_waste;

    @Column(name = "category_as_per_schedule", nullable = true,length = 200)
    private String category_as_per_schedule;

    @Column(name = "existing_as_per_ec", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float existing_as_per_ec;

    @Column(name = "generation_per_year_existing_unit", nullable = true,length = 50)
    private String unit_1;

    @Column(name = "after_changing_in_product", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float after_changing_in_product;

    @Column(name = "unit_2", nullable = true,length = 50)
    private String unit_2;

    @Column(name = "source_of_generation", nullable = true,length = 200)
    private String source_of_generation;

    @Column(name = "mode_of_storage", nullable = true,length = 200)
    private String mode_of_generation;

    @Column(name = "mode_of_treatment", nullable = true,length = 200)
    private String mode_of_treatment;



}
