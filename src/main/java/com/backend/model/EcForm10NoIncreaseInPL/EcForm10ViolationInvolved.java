package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;



@Data
@Entity
@Table(name = "ec_form10_violation_involved", schema = "master")
public class EcForm10ViolationInvolved extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "violation_involved", length = 255)
    private String violation_involved;

    @Column(name = "type_of_violation", length = 255)
    private String type_of_violation;

    @Column(name = "work_period_from")
    private String work_period_from;

    @Column(name = "violation_others_specify", length = 50)
    private String violation_others_specify;

    @Column(name = "work_period_to")
    private String work_period_to;
}
