package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "standard_tor_certificate_general_conditions", schema = "master")
public class StandardTorCertificateGeneralConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "generalConditions", length = 500, nullable = true)
    private String generalConditions;
}