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
@Table(name = "ec_form10_details_of_effluent_generation_quality", schema = "master")
public class EcForm10DetailsOfEffluentGenerationQuality extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "composition_as_per_the_ec", nullable = true,length = 200)
    private String raw_material_cas_number;

    @Column(name = "concentration_as_per_the_ec", nullable = true)
    private String quantity_permitted;

    @Column(name = "composition_after_proposed", nullable = true,length = 200)
    private String raw_material;

    @Column(name = "concentration_after_proposed", nullable = true)
    private String quantity_proposed;

    @Column(name = "remarks", nullable = true,length = 500)
    private String remarks_if;
}
