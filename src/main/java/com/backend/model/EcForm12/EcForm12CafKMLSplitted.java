package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.backend.model.DocumentDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ec_form_12_caf_kml_splitted", schema = "master")
public class EcForm12CafKMLSplitted extends Auditable<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(targetEntity = EcForm12CAFKMLLPlotsSplitted.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form_12_caf_kml_splitted_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private List<EcForm12CAFKMLLPlotsSplitted> projectLocationSplitted = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private AppConstant.kml_type type;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caf_kml_id", nullable = true)
    private DocumentDetails patch_kml_splitted;

    @Column(nullable = true, length = 1000)
    private String remarks;

    @Column(name = "ne_extend_splitted", length = 100, nullable = true)
    private String ne_extend_splitted;

    @Column(name = "sw_extend_splitted", length = 100, nullable = true)
    private String sw_extend_splitted;

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    public EcForm12CafKMLSplitted() {

        this.is_active = true;
        this.is_deleted = false;
    }
}