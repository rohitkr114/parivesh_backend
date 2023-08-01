package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_expansion_NIPL_certificate_specific_ec_conditions", schema = "master")
public class EcExpansionNIPLCertificateSpecificEcConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "specificEcCondition", length = 1000, nullable = true)
    private String  specificEcCondition;
}
