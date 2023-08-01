package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_mining_expansion_template_specific_ec_conditions", schema = "master")
public class EcMiningExpansionTemplateSpecificEcConditions {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "specificEcCondition", length = 1000, nullable = true)
    private String  specificEcCondition;
}
