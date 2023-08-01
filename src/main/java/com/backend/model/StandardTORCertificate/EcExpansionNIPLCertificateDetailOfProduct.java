package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_expansion_NIPL_certificate_detail_of_project", schema = "master")
public class EcExpansionNIPLCertificateDetailOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_of_the_product")
    private String name_of_the_product;

    @Column(name = "byProduct")
    private String byProduct;

    @Column(name = "quantity_Existing")
    private String quantity_Existing;

    @Column(name = "quantity_Proposed")
    private String quantity_Proposed;

    @Column(name = "quantity_Total")
    private String quantity_Total;

    @Column(name = "unit")
    private String unit;

    @Column(name = "mode_of_transport")
    private String mode_of_transport;

    @Column(name = "remarks")
    private String remarks;
}
