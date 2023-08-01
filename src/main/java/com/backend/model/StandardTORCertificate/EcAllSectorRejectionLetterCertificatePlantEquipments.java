package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_all_sector_rejection_letter_certificate_plant_equipments", schema = "master")
public class EcAllSectorRejectionLetterCertificatePlantEquipments {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plant")
    private String Plant;

    @Column(name = "configuration")
    private String Configuration;

    @Column(name = "remarksIfAny")
    private String remarksIfAny;

}

