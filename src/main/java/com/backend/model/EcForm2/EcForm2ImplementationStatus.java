package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ec_form_2_implementation_status",schema = "master")
public class EcForm2ImplementationStatus extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "implemented_unit_details",columnDefinition = "text")
    private String implementedUnitDetails;

    @Column(name = "unImplemented_unit_details",columnDefinition = "text")
    private String unImplementedUnitDetails;

    @Column(columnDefinition = "text")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_2_id", nullable = true)
    @JsonIgnore
    private EcForm2ProjectDetails ecForm2;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted=false;
}
