package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.backend.model.Activities;
import com.backend.model.SubActivities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ec_form_2_project_activity_details",schema = "master")
public class EcForm2ProjectActivityDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
    String activity_type;

    @Column(nullable = true)
    String threshold;

    String proposalNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_2_id", nullable = true)
    @JsonIgnore
    private EcForm2ProjectDetails ecForm2;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;

}
