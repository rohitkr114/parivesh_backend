package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.ForestClearancePartB.ForestClearanceBBasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_transfree_details", schema = "master")
public class EcForm12TransfreeDetails extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form12_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm12 ecForm12;

    @Column(nullable = true)
    private String transferer_company_name;
    @Column(nullable = true)
    private String transferer_company_house;
    @Column(nullable = true)
    private String transferer_company_village;
    @Column(nullable = true)
    private String transferer_company_district;
    @Column(nullable = true)
    private String transferer_company_state;
    @Column(nullable = true)
    private String transferer_company_pincode;
    @Column(nullable = true)
    private String transferer_company_landmark;
    @Column(nullable = true)
    private String transferer_company_email;
    @Column(nullable = true)
    private String transferer_company_landline;
    @Column(nullable = true)
    private String transferer_company_legal_status_other;
    @Column(nullable = true)
    private String transferer_company_mobile;
    @Column(nullable = true)
    private String transferer_company_legal_status;

    @Column(nullable = true)
    private String type_of_transferee;

    @Column(nullable = false)
    private Boolean is_active;

    @Column(nullable = false)
    private Boolean is_deleted;

    public EcForm12TransfreeDetails() {
        this.is_active = true;
        this.is_deleted = false;
    }
}
