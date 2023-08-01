package com.backend.model.EcForm8TransferOfTOR;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 *Details of Terms of Reference
 */
@Data
@Entity
@Table(name = "ec_form8_detail_of_tor", schema = "master")
public class EcForm8DetailOfTOR extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_8_detail_of_tor_id", nullable = true)
    @JsonIgnore
    private EcForm8TransferOfTOR ecForm8TransferOfTOR;

    @Column(name = "date_of_issue_environmental_clearance")
    private Date date_of_issue_environmental_clearance;

    @Column(name = "moef_file_no", length = 100)
    private String moef_file_no;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_chronology_tor", nullable = true)
    private DocumentDetails upload_chronology_tor;

    @OneToMany(targetEntity = EarlierTORAmendmentDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form8_detail_of_tor_id", referencedColumnName = "id")
    private Set<EarlierTORAmendmentDetails> uploadTOR;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_ec_letter_id", nullable = true)
    private DocumentDetails copy_of_ec_letter;

    @Column(name = "is_tor_obtained")
    private Boolean is_tor_obtained;

}
