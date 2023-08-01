package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_mining_expansion_template_detail_of_mineral_product", schema = "master")
public class EcMiningExpansionTemplateDetailOfMineralProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_of_the_mineral")
    private String name_of_the_mineral;

    @Column(name = "classification_of_mineral")
    private String classification_of_mineral;

    @Column(name = "production_capacity")
    private String production_capacity;

    @Column(name = "mode_of_transport")
    private String mode_of_transport;

}
