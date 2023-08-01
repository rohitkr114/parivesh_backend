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
@Table(name = "ec_form10_details_of_raw_material", schema = "master")
public class EcForm10DetailsOfRawMaterial extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "list_of_raw_materials", nullable = true,length = 200)
    private String raw_material_cas_number;

    @Column(name = "quantity_permitted", nullable = true)
    private String quantity_permitted;

    @Column(name = "list_of_raw_materials_proposed", nullable = true,length = 200)
    private String raw_material;

    @Column(name="unit_permitted",nullable = true)
    private String unit_permitted;
    
    @Column(name="unit_proposed",nullable = true)
    private String unit_proposed;
    
    @Column(name = "quantity_proposed", nullable = true)
    private String quantity_proposed;

    @Column(name = "remarks", nullable = true,length = 500)
    private String remarks_if;
}
