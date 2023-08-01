package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ec_form_2_enclosure_details",schema = "master")
public class EcForm2EnclosureDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_2_id", nullable = true)
    @JsonIgnore
    private EcForm2ProjectDetails ecForm2;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_letter_id", nullable = true)
    private DocumentDetails coverLetter;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "previous_clearance_letter_id", nullable = true)
    private DocumentDetails previousClearanceLetter;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "latest_compliance_report_id", nullable = true)
    private DocumentDetails latestComplianceReport;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted=false;
}
