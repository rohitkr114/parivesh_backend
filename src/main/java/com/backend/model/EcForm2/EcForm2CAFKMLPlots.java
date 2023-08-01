package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_2_caf_kml_plots", schema = "master")
public class EcForm2CAFKMLPlots extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "toposheet_no", length = 50, nullable = false)
    private String toposheet_no;

    @Column(name = "state", length = 50, nullable = false)
    private String state;

    @Column(name = "state_code",length = 50, nullable = true)
    private String state_code;

    @Column(name = "district_code",length = 50, nullable = true)
    private String district_code;

    @Column(name = "village_code",length = 50, nullable = true)
    private String village_code;

    @Column(name = "district", length = 50, nullable = false)
    private String district;

    @Column(name = "sub_district", length = 50, nullable = false)
    private String Sub_District;
    @Column(name = "sub_district_code", length = 50, nullable = true)
    private String sub_district_code;
    @Column(name = "village", length = 50, nullable = false)
    private String village;

    @Column(name = "plot_no", length = 1000)
    private String plot_no;

    @Column(name = "is_active",nullable = false)
    private Boolean is_active=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean is_deleted=false;

    @Column(name = "manual_entry", nullable = true)
    private Boolean manual_entry;
}
