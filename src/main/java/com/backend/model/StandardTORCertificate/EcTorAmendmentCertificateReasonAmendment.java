package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_tor_amendment_certificate_reason_amendment", schema = "master")
public class EcTorAmendmentCertificateReasonAmendment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "reasonForAmendment")
    private String reasonForAmendment;

}
