package com.backend.model.certificate;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clearance_matrix", schema = "master")
public class ClearanceMatrix extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "application_id")
    private Integer application_id;

    @Column(name = "category", length = 100, nullable = true)
    private String category;

    @Column(name = "sub_category", length = 100, nullable = true)
    private String sub_category;

    @Column(name = "condition", length = 1000)
    private String condition;

    @Column(name = "activity_id", nullable = true)
    private Integer activity_id;

    @Column(name = "sub_activity_id", nullable = true)
    private Integer sub_sctivity_id;

    @Column(name = "sector", length = 100, nullable = true)
    private String sector;

    @Column(name = "type_proposal", length = 50, nullable = true)
    private String type_proposal;

    @Column(name = "sectorname", length = 50, nullable = true)
    private String sectorname;

    @Column(name = "heading", length = 500, nullable = true)
    private String heading;

    @Column(name = "condition_heading", length = 500, nullable = true)
    private String condition_heading;

    @Column(name = "condition_subheading", length = 1000, nullable = true)
    private String condition_subheading;

    @Column(name = "condition_sub_subheading", length = 1000, nullable = true)
    private String condition_sub_subheading;

    @Column(name = "order_range", nullable = true)
    private Integer order_range;

    @Column(name = "is_active", nullable = true)
    private Boolean is_active;

    @Column(name = "is_delete", nullable = true)
    private Boolean is_delete;

    @Column(name = "is_common", nullable = true)
    private Boolean is_common;

    private ClearanceMatrix() {
        this.is_active = true;
        this.is_delete = false;
        this.is_common = false;
    }
}
