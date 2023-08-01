package com.backend.model.ForestClearancePartB;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "forest_clearance_part_b_ca_land_details", schema = "master")
public class ForestClearanceBCaLandDetails extends Auditable<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    ForestClearanceBCaLandDetails() {
        this.is_active = true;
        this.is_deleted = false;
    }

}
