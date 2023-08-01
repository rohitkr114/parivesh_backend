package com.backend.model.EcForm9TransferOfEC;


import com.backend.model.DocumentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form9_government_order_of_ec", schema = "master")
public class EcForm9GovernmentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "violation_description")
    private String violation_description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "working_plan_prescription_copy_id", nullable = true)
    private DocumentDetails working_plan_prescription_copy;


}
