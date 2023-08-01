package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="ec_form_2_project_implementation_status",schema = "master")
public class EcForm2ProjectImplementationStatus extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_2_id", nullable = true)
    @JsonIgnore
    private EcForm2ProjectDetails ecForm2;

    @Column(name = "type_of_proposal")
    private String typeOfProposal;

    @Column(name = "implementation_status")
    private String implementationStatus;

    //implementation status options start
    // for option 1

    @Column(columnDefinition = "text")
    private String reason;

    //for option 2 and 5

    private String referenceNo;

    @Column(name = "consent_date")
    private Date consentDate;

    @Column(name = "consent_validity_date")
    private Date consentValidityDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "consent_copy_id", nullable = true)
    private DocumentDetails consentCopy;

    //for option 3

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "development_status_copy_id", nullable = true)
    private DocumentDetails developmentStatusCopy;

    //implementation status options end

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "corrigendum_letter_copy_id", nullable = true)
    private DocumentDetails corrigendumLetterCopy;

    @Column(name = "corrigendum_justification",columnDefinition = "text")
    private String corrigendumJustification;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted=false;



}
