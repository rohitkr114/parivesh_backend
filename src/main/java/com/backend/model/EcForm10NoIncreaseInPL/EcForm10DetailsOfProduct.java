package com.backend.model.EcForm10NoIncreaseInPL;


import com.backend.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_details_of_product", schema = "master")
public class EcForm10DetailsOfProduct extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "list_of_products_permitted", nullable = true,length = 200)
    private String cto_no;

    @Column(name = "quantity_permitted", nullable = true)
    private String cto_quantity;

    @Column(name = "list_of_products_proposed", nullable = true,length = 200)
    private String cas_no;
    
    @Column(name = "cto_unit_permitted", nullable = true)
    private String cto_unit;

    @Column(name = "cas_unit_list_of_products_proposed", nullable = true,length = 200)
    private String cas_unit;

    @Column(name = "quantity_proposed", nullable = true)
    private String quantity_tpd;

    @Column(name = "remarks", nullable = true,length = 500)
    private String remarks;

}
