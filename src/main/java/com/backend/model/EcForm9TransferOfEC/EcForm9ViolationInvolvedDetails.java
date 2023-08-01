package com.backend.model.EcForm9TransferOfEC;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "ec_form9_violation_involved_details", schema = "master")
public class EcForm9ViolationInvolvedDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "is_regulatory_authority",nullable = true , length = 100)
    private String regulatory_authority;

    @Column(name = "direction_others")
    private String direction_others;
    
    @Column(name = "is_direction",length = 500)
    private String is_direction;

    @Column(name = "details_direction_issued",length = 500)
    private String details_direction_issued;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "direction_penalty_id", nullable = true)
    private DocumentDetails direction_penalty_id;

    @Column(name = "brief_summary",length = 1000)
    private String brief_summary;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_violation_id", nullable = true)
    private DocumentDetails report_on_violation;
}
