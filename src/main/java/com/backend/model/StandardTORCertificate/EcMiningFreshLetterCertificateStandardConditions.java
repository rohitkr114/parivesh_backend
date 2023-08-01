package com.backend.model.StandardTORCertificate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_mining_fresh_letter_certificate_standard_condition", schema = "master")
public class EcMiningFreshLetterCertificateStandardConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "standard_condition")
    private String standardEcConditions;
}
