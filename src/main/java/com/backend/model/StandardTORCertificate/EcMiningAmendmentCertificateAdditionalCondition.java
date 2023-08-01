package com.backend.model.StandardTORCertificate;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_mining_amendment_certificate_additional_certificate", schema = "master")
public class EcMiningAmendmentCertificateAdditionalCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "additionalSpecificCondition")
    private String additionalSpecificCondition;
}
