package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.backend.model.DocumentDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "ec_form_2_caf_kml", schema = "master")
public class EcForm2CafKML extends Auditable<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(targetEntity = EcForm2CAFKMLPlots.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form_2_caf_kml_id",referencedColumnName = "id",nullable = false)
    @EqualsAndHashCode.Exclude
    private Set<EcForm2CAFKMLPlots> cafKMLPlots=new HashSet<>();

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

    @Column(name = "is_active",nullable = false)
    private Boolean isActive=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted=false;
}
