package com.backend.model.StandardTORCertificate;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_mining_amendment_certificate_plant_amendment", schema = "master")
public class EcMiningAmendmentCertificatePlantAmendment {

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
