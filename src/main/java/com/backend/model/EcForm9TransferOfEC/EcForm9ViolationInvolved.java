package com.backend.model.EcForm9TransferOfEC;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "ec_form9_violation_involved", schema = "master")
public class EcForm9ViolationInvolved extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "violation_involved", length = 255)
    private String violation_involved;

    @Column(name = "type_of_violation", length = 255)
    private String type_of_violation;

    @Column(name = "violation_others", length = 255)
    private String violation_others;

    @Column(name = "work_period_from")
    private String work_period_from;

    @Column(name = "work_period_to")
    private String work_period_to;


}
