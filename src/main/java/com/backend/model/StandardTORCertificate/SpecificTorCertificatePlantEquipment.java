package com.backend.model.StandardTORCertificate;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "specific_tor_certificate_plant_equipment", schema = "master")
public class SpecificTorCertificatePlantEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plant_equipment")
    private String plant_equipment;

    @Column(name = "configuration")
    private String configuration;

    @Column(name = "remarks")
    private String remarks;

    public SpecificTorCertificatePlantEquipment (String plant_equipment, String configuration, String remarks) {
        this.plant_equipment = plant_equipment;
        this.configuration = configuration;
        this.remarks = remarks;
    }
}
