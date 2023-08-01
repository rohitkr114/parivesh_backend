package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_mining_expansion_template_standard_ec_conditions", schema = "master")
public class EcMiningExpansionTemplateStandardEcConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "standardEcCondition", length = 1000, nullable = true)
    private String standardEcCondition;

}
