package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ec_form_2_corrigendum_descripton",schema = "master")
public class EcForm2CorrigendumDescription extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "approved_letter_reference")
    private String approvedLetterReference;

    private String slNo;

    private String description;

    @Column(name = "corrigendum_sought")
    private String corrigendumSought;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = true)
    private DocumentDetails document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_2_id", nullable = true)
    @JsonIgnore
    private EcForm2ProjectDetails ecForm2;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted=false;
}
