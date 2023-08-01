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
@Table(name = "ec_form10_details_of_effluent_generation_quantity", schema = "master")
public class EcForm10DetailsOfEffluentGenerationQuantity extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "propose", nullable = true)
    private String cto_no;

    @Column(name = "quantity_of_existing_effluent_generation_in_kld", nullable = true)
    private String cto_quantity;

    @Column(name = "quantity_of_effluent_generation_after_proposed", nullable = true)
    private String cas_number;

    @Column(name = "mode_of_disposal", nullable = true,length = 200)
    private String quantity_tpd;


}
