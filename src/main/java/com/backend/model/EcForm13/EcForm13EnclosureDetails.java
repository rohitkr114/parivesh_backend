package com.backend.model.EcForm13;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ec_form13_enclosure_details",schema = "master")
public class EcForm13EnclosureDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_letter_id", nullable = true)
    private DocumentDetails coverLetter;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_summary_id", nullable = true)
    private DocumentDetails projectSummary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ec_form13_id",nullable = false)
    @JsonIgnore
    private EcForm13ProjectDetails ecForm13;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;
}
