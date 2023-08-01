package com.backend.model.EcForm10NoIncreaseInPL;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_hazardous_waste_generation_monitoring", schema = "master")
public class EcForm10HazardousWasteGenerationMonitoringSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "attribute", nullable = true, length = 10)
    private String attribute;

    @Column(name = "constituents", nullable = true, length = 200)
    private String constituents;

    @Column(name = "date_of_installation", nullable = true)
    private Date date_of_installation;

    @Column(name = "details_of_calibration_of_ocms", nullable = true, length = 200)
    private String details_of_calibration;

    @Column(name = "no_of_time", nullable = true)
    private String no_of_time;

    @Column(name = "value_exceeded", nullable = true)
    @DecimalMin(value = "0.0000")
    private Float value_exceeded;

    @Column(name = "status_of_ocms_functioning", nullable = true, length = 10)
    private String status_of_ocms;

    @Column(name = "is_cpcb", nullable = true, length = 10)
    private String is_cpcb;

    @Column(name = "is_spcb", nullable = true, length = 10)
    private String is_spcb;

    @Column(name = "cpcb", nullable = true)
    private Date cpcb;

    @Column(name = "spcb", nullable = true)
    private Date spcb;


}
