package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_tor_amendment_certificate_detail_of_product", schema = "master")
public class EcTorAmendmentCertificateDetailOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "existing")
    private String existing;

    @Column(name = "proposedAmendment")
    private String proposedAmendment;

}
