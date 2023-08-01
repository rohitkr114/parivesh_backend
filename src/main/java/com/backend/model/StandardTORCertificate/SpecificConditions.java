package com.backend.model.StandardTORCertificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "specific_conditions", schema = "master")
public class SpecificConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "specific_conditions", columnDefinition = "text", nullable = true)
    private String specificConditions;
}
