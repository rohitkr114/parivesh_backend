package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.Activities;
import com.backend.model.SubActivities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_10_proj_activity_dtls", schema = "master")
public class EcForm10ProjectActivityDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form10_basic_information_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm10BasicInformation ecForm10BasicInformation;

    @Transient
    private Integer activityId;

    @Transient
    private Integer subActivityId;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activities activities;

    @ManyToOne
    @JoinColumn(name = "subactivity_id")
    private SubActivities subActivities;

    @Column(nullable = true)
    private String activity_type;

    @Column(nullable = true)
    private String threshold;

    private String proposalNo;

    @Column(nullable = true)
    private Boolean isDelete;

    public EcForm10ProjectActivityDetails() {
        this.isDelete = false;
    }


}
