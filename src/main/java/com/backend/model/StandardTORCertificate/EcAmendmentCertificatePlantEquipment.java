package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_amendment_certificate_plant_equipment", schema = "master")
public class EcAmendmentCertificatePlantEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plant_equipment")
    private String plant_equipment;

    @Column(name = "existingConfiguration")
    private String existingConfiguration;

    @Column(name = "proposedConfiguration")
    private String proposedConfiguration;

    @Column(name = "finalConfiguration")
    private String finalConfiguration;

    @Column(name = "remarksIfAny")
    private String remarksIfAny;
}
