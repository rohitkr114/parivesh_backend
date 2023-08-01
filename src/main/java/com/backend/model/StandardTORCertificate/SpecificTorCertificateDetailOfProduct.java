package com.backend.model.StandardTORCertificate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "specific_tor_certificate_detail_of_product", schema = "master")
public class SpecificTorCertificateDetailOfProduct {

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
    
	@Column(name = "proposed_quantity")
    private Double proposed_quantity;
    
	@Column(name="quantity_total")
	private String quantity_total;

    public SpecificTorCertificateDetailOfProduct(String name_of_the_product, String byProduct, Double quantity, String unit, String mode_of_transport, String remarks, Double proposed_quantity, String quantity_total) {
        this.name_of_the_product = name_of_the_product;
        this.byProduct = byProduct;
        this.quantity = quantity;
        this.unit = unit;
        this.mode_of_transport = mode_of_transport;
        this.remarks = remarks;
	    this.proposed_quantity = proposed_quantity;
        this.quantity_total = quantity_total;
    }
}
