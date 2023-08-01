package com.backend.model.EcForm6Part2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_impl_product_pro_bypro_detail", schema = "master")
public class EcForm6ProductProByprodetails extends Auditable<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "details_pro_bypro_pname", length = 100, nullable = true)
    private String product_name;

    @Column(name = "details_pro_bypro_whether", nullable = true)
    private String product_by;


    @Column(name = "details_pro_bypro_quantity", nullable = true)
    //private Integer quantity;
    //999999999999999999999999999999999999999999999.9999
    private String quantity;

    @Column(name = "details_pro_bypro_units", nullable = true)
    private String unit;

    @Column(name = "details_pro_bypro_mtransport", nullable = true)
    private String transport_mode;

    @Column(name = "details_pro_bypro_remarks", length = 100, nullable = true)
    private String remarks;

    @Column(name = "details_pro_bypro_quantity_proposed_increament")
    private String proposed_quantity;

}
