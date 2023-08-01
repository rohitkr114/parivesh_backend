package com.backend.model.StandardTORCertificate;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_tor_amendment_certificate_plant_equipment", schema = "master")
public class EcTorAmendmentCertificatePlantEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plantEquipment")
    private String plantEquipment;

    @Column(name = "existingConfiguration")
    private String existingConfiguration;

    @Column(name = "proposedConfiguration")
    private String proposedConfiguration;

    @Column(name = "finalConfiguration")
    private String finalConfiguration;

    @Column(name = "remarks")
    private String remarks;
}
