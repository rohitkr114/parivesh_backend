package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_amendment_certificate_additional_certificate", schema = "master")
public class EcAmendmentCertificateAdditionalCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "additionalSpecificCondition")
    private String additionalSpecificCondition;

}
