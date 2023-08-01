package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_amendment_certificate_other_amendment", schema = "master")
public class EcAmendmentCertificateOtherAmendment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "existing")
    private String existing;

    @Column(name = "proposedAmendment")
    private String proposedAmendment;
}
