package com.backend.model.StandardTORCertificate;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_fresh_letter_certificate_plant_equipment", schema = "master")
public class EcFreshLetterCertificatePlantEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plant_equipment")
    private String plant_equipment;

    @Column(name = "configuration")
    private String configuration;

    @Column(name = "remarks")
    private String remarks;
}
