package com.backend.model.EcForm9TransferOfEC;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form9_toposheet_no", schema = "master")
public class EcForm9ToposheetNo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "toposheet_no")
    private String toposheet_no;

    @Column(name = "immutable")
    private boolean immutable;
}
