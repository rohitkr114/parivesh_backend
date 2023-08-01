package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_expansion_NIPL_certificate_plant_equipment", schema = "master")
public class EcExpansionNIPLCertificatePlantEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plant_equipment")
    private String plant_equipment;

    @Column(name = "existing_configuration")
    private String existing_configuration;

    @Column(name = "proposed_configuration")
    private String proposed_configuration;

    @Column(name = "remarks")
    private String remarks;

}
