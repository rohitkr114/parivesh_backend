package com.backend.model.FcFormEPartB;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fc_form_e_part_b_patch_wise_details", schema = "master")
public class ForestClearanceEPatchWiseDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fc_form_e_part_b_patch_id", nullable = true)
    @JsonIgnore
    private ForestClearanceEPatches forestClearanceEPatches;

    private String patch_no;

    private Integer district_code;

    @Column(length = 100)
    private String district_name;

    @Column(length = 100)
    private String sub_district;

    @Column(length = 100, nullable = true)
    private String ca_land_category;

    private Double area;

    @Column(length = 100)
    private String pf_rf_name;

    @Column(length = 100)
    private String range;

    @Column(length = 100)
    private String village_name;

    @Column(length = 100)
    private String survey_no;

    @Column(length = 1000)
    private String plot_no;

    @Column(length = 100)
    private String site_name;

    @Column(nullable = true)
    private Integer division_id;

    @Column(length = 50, nullable = true)
    private String division_name;

    @Column(nullable = true)
    private Boolean manual_entry;

    @Column(nullable = true)
    private Integer sub_district_code;

    @Column(nullable = true)
    private Integer village_code;

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    ForestClearanceEPatchWiseDetails() {
        this.is_active = true;
        this.is_deleted = false;
    }

}
