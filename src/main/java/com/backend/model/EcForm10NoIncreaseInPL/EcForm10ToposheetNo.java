package com.backend.model.EcForm10NoIncreaseInPL;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Data
@Entity
@Table(name = "ec_form10_toposheet_no", schema = "master")
public class EcForm10ToposheetNo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "toposheet_no")
    private String toposheet_no;

    @Column(name = "immutable")
    private boolean immutable;

}

