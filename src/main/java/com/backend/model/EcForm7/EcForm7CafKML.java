package com.backend.model.EcForm7;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ec_form_7_caf_kml", schema = "master")
public class EcForm7CafKML extends Auditable<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(targetEntity = EcForm7CAFKMLPlots.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form_7_caf_kml_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private List<EcForm7CAFKMLPlots> cafKMLPlots = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private AppConstant.kml_type type;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caf_kml_id", nullable = true)
    private DocumentDetails caf_kml;

    @Column(nullable = true, length = 1000)
    private String remarks;

    @Column(name = "ne_extend", length = 100, nullable = true)
    private String ne_extend;

    @Column(name = "sw_extend", length = 100, nullable = true)
    private String sw_extend;

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    public EcForm7CafKML() {

        this.is_active = true;
        this.is_deleted = false;
    }
}