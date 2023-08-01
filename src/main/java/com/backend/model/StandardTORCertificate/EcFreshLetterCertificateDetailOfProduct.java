package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_fresh_letter_certificate_detail_of_product", schema = "master")
public class EcFreshLetterCertificateDetailOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_of_the_product")
    private String name_of_the_product;

    @Column(name = "byProduct")
    private String byProduct;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "mode_of_transport")
    private String mode_of_transport;

    @Column(name = "remarks")
    private String remarks;
}
