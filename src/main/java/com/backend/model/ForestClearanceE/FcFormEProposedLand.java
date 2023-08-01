package com.backend.model.ForestClearanceE;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fc_form_e_proposed_land", schema = "master")
public class FcFormEProposedLand extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//	@Column(nullable = true)
//	private String division;
//	
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "upload_noc_copy_id", nullable = true)
//	private DocumentDetails kml_patch_copy;
//	
//	@Column(nullable = true, length = 1000)
//	private String remarks;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_noc_copy_id", nullable = true)
    private DocumentDetails kml_patch_copy;

    @Column(nullable = true, length = 1000)
    private String remarks;

    @Column(nullable = true)
    private String component;

    @Column(nullable = true)
    private Boolean is_project_affected_trees;

    @Column(nullable = true)
    private Double original_layout_plan;

    @Column(nullable = true)
    private Double changed_layout_plan;

    @Column(nullable = true)
    private Double net_change;

    @Column(nullable = true)
    private Double total_forest_land_approved;

    @Column(nullable = true)
    private Double total_forest_land_proposed;

    @Column(nullable = true)
    private Double total_non_forest_land_approved;

    @Column(nullable = true)
    private Double total_non_forest_land_proposed;

    @Column(nullable = true)
    private String project_category;

    @Column(nullable = true)
    private Integer project_category_id;

    @Column(nullable = true)
    private String project_category_other;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fc_form_e_id", nullable = true)
    @JsonIgnore
    private FcFormE fcFormE;

    @Column(nullable = true)
    private Boolean is_active;

    @Column(nullable = true)
    private Boolean is_delete;

    public FcFormEProposedLand() {
        this.is_active = true;
        this.is_delete = false;
    }

    public FcFormEProposedLand(Integer id) {
        this.id = id;
    }

}
