package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_details_of_components", schema = "master")
public class ECForm12DetailsOfComponents extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_12_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm12 ecForm12;
    @Column(name = "facility_prior_clearance", columnDefinition = "text", nullable = true)
    private String facilityPriorClearance;

    @Column(name = "term_condition_proposed", columnDefinition = "text", nullable = true)
    private String termConditionPropsed;

    @Column(name = "consolidated_ec_condition", columnDefinition = "text", nullable = true)
    private String consolidatedEcCondition;


    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    public ECForm12DetailsOfComponents() {
        this.is_active = true;
        this.is_deleted = false;
    }
}
