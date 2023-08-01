package com.backend.model.EcForm11;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="ec_form_11_other_details",schema="master")
public class EcForm11OtherDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="ec_granted_date")
    private Date ecGrantedDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_grant_copy_id", nullable = true)
    private DocumentDetails ecGrantCopy;

    @Column(name="ec_implementation_status",length=25)
    private String ecImplementationStatus;

    @Column(name="ec_implementation_details",length=1000)
    private String ecImplementationDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_implementation_plan_id", nullable = true)
    private DocumentDetails ecImplementationPlan;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_implementation_plan_versus_consent_id", nullable = true)
    private DocumentDetails ecImplementationPlanVersusConsent;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "satellite_image_copy_id", nullable = true)
    private DocumentDetails satelliteImageCopy;

    @Column(name="reason_for_surrender",length=5000)
    private String reasonForSurrender;

    @Column(name = "ec_obtained_select",nullable = true)
    private String ecObtainedSelect;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "factual_report_id", nullable = true)
    private DocumentDetails factualReport;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form11_id", nullable = false)
    @JsonIgnore
    private EcForm11ProjectDetails ecForm11ProjectDetails;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
