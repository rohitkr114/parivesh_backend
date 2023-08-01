package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_caf_kml_plots", schema = "master")
public class EcForm12CAFKMLPlots extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "toposheet_no", length = 50, nullable = false)
    private String toposheet_no;

    @Column(name = "state", length = 50, nullable = false)
    private String State;

    @Column(length = 50, nullable = true)
    private String state_code;

    @Column(length = 50, nullable = true)
    private String district_code;

    @Column(length = 50, nullable = true)
    private String village_code;

    @Column(name = "district", length = 50, nullable = false)
    private String District;

    @Column(name = "sub_district", length = 50, nullable = false)
    private String Sub_District;
    @Column(name = "sub_district_code", length = 50, nullable = true)
    private String sub_district_code;
    @Column(name = "village", length = 50, nullable = false)
    private String Village;

    @Column(name = "plot_no", length = 1000)
    private String plot_no;

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    @Column(name = "manual_entry", nullable = true)
    private Boolean manual_entry;

    public Boolean isManual_entry() {
        return manual_entry;
    }

    public void setManual_entry(Boolean manual_entry) {
        if (manual_entry == true) {
            this.manual_entry = manual_entry;
        } else {
            this.manual_entry = false;
        }

    }

    public EcForm12CAFKMLPlots() {
        this.is_active = true;
        this.is_deleted = false;
    }


}
