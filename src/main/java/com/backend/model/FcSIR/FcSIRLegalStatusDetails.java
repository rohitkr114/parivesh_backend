package com.backend.model.FcSIR;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fc_sir_legal_status_details",schema = "authority")
public class FcSIRLegalStatusDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="forest_type")
    private String forestType;

    private Double area;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fc_sir_id",nullable = false)
    @JsonIgnore
    private FcSiteInspectionReport fcSiteInspectionReport;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
