package com.backend.model.StandardTORCertificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_mining_fresh_letter_certificate_detail_of_minerals", schema = "master")
public class EcMiningFreshLetterCertificateDetailOfMinerals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_of_mineral")
    private String name_of_mineral;

    @Column(name = "classification_on_mineral")
    private String classification_on_mineral;

    @Column(name = "production_on_capacity")
    private String production_on_capacity;

    @Column(name = "mode_of_transport")
    private String mode_of_transport;
}
