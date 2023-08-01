package com.backend.model.StandardTORCertificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ec_all_sector_rejection_letter_certificate_detail_of_product", schema = "master")
public class EcAllSectorRejectionLetterCertificateDetailOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_of_the_product")
    private String name_of_the_product;

    @Column(name = "byProduct")
    private String byProduct;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "mode_of_transport")
    private String mode_of_transport;

    @Column(name = "remarks")
    private String remarks;

    public EcAllSectorRejectionLetterCertificateDetailOfProduct(String name_of_the_product, String byProduct, Double quantity, String unit, String mode_of_transport, String remarks) {
        this.name_of_the_product = name_of_the_product;
        this.byProduct = byProduct;
        this.quantity = quantity;
        this.unit = unit;
        this.mode_of_transport = mode_of_transport;
        this.remarks = remarks;
    }
}
