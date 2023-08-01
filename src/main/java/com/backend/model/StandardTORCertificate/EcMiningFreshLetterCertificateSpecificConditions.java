package com.backend.model.StandardTORCertificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_mining_fresh_letter_certificate_specific_condition", schema = "master")
public class EcMiningFreshLetterCertificateSpecificConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "specific_condition")
    private String specificEcConditions;
}
