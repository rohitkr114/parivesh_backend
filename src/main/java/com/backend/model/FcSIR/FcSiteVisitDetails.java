package com.backend.model.FcSIR;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="fc_site_visit_details",schema = "authority")
public class FcSiteVisitDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="official_name",length = 100)
    private String officialName;

    @Column(name="designation",length = 100)
    private String designation;

    @Column(name="department_name",length = 100)
    private String departmentName;

    @Column(name="visit_date",length = 100)
    private Date visitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fc_sir_id",nullable = false)
    @JsonIgnore
    private FcSiteInspectionReport fcSiteInspectionReport;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
