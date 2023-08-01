package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;



@Data
@Entity
@Table(name = "ec_form10_violation_involved_details", schema = "master")
public class EcForm10ViolationInvolvedDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "is_direction")
    private String is_direction;

    @Column(name = "violation_others_direction")
    private String direction_others;

    @Column(name = "details_direction_issued")
    private String details_direction_issued;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "direction_penalty_id", nullable = true)
    private DocumentDetails direction_penalty;

    @Column(name = "brief_summary")
    private String brief_summary;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_on_violation_id", nullable = true)
    private DocumentDetails report_on_violation;


}
