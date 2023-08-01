package com.backend.model.FcSIR;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fc_sir_other_details",schema = "authority")
public class FcSIROtherDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="no_of_trees")
    private Integer noOfTrees;

    @Column(name="canopy_density")
    private Float canopyDensity;

    @Column(name="forest_area_violated")
    private Double forestAreaViolated;

    @Column(name="action_taken_details",length = 500)
    private String actionTakenDetails;

    @Column(name="wildlife_details",columnDefinition = "TEXT")
    private String wildlifeDetails;

    @Column(name="distance_from_protected_area")
    private Double distanceFromProtectedArea;

    @Column(name="remarks",columnDefinition = "TEXT")
    private String remarks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fc_sir_id",nullable = false)
    @JsonIgnore
    private FcSiteInspectionReport fcSiteInspectionReport;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
