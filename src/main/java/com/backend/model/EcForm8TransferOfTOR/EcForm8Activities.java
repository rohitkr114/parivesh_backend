package com.backend.model.EcForm8TransferOfTOR;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ec_form8_minor_activity_sel", schema = "master")
public class EcForm8Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "activity_id")
    private String activityId;

    @Column(name = "sub_activity_id")
    private String subActivityId;

    @Column(name = "activity_type")
    private String activity_type;

    @OneToMany(targetEntity = EcForm8Threshold.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form8_minor_activity_sel_id", referencedColumnName = "id")
    private Set<EcForm8Threshold> threshold = new HashSet<>();
}
