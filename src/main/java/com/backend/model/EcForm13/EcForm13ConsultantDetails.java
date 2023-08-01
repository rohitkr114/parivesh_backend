package com.backend.model.EcForm13;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ec_form13_consultant_details",schema = "master")
public class EcForm13ConsultantDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "is_eia_consultant_engaged",nullable = true)
    private Boolean isEIAConsultantEngaged;

    @Column(name = "no_eia_consultant_engaged_reason",nullable = true, length = 500)
    private String noEIAConsultantEngagedReason;

    @Column(name = "organisation_id",nullable = true, length = 500)
    private String organisationId;

    @Column(name = "consultant_name",nullable = true, length = 500)
    private String consultantName;

    @Column(name = "consultant_address",nullable = true, length = 1000)
    private String consultantAddress;

    @Column(name = "consultant_mobile",nullable = true, length = 255)
    private String consultantMobile;

    @Column(name = "consultant_email",nullable = true, length = 255)
    private String consultantEmail;

    @Column(name = "consultant_category",nullable = true, length = 500)
    private String consultantCategory;

    @Column(name = "consultant_sectors",nullable = true, length = 1000)
    private String consultantSectors;

    @Column(name = "validity_of_accreditation",nullable = true, length = 500)
    private String validityOfAccreditation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ec_form13_id",nullable = false)
    @JsonIgnore
    private EcForm13ProjectDetails ecForm13;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
