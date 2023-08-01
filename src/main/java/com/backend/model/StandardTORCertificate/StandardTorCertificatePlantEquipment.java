package com.backend.model.StandardTORCertificate;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "standard_tor_certificate_plant_equipment", schema = "master")
public class StandardTorCertificatePlantEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plant_equipment")
    private String plant_equipment;

    @Column(name = "configuration")
    private String configuration;

    @Column(name = "remarks")
    private String remarks;

    public StandardTorCertificatePlantEquipment (String plant_equipment, String configuration, String remarks) {
        this.plant_equipment = plant_equipment;
        this.configuration = configuration;
        this.remarks = remarks;
    }
}
