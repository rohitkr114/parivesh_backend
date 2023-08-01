package com.backend.model.StandardTORCertificate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "standard_tor_cert_general_cond", schema = "master")
public class StandardTorCertificatesGeneralCond {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "gen_cond_id")
    private Integer genCondId;
    @Column(name = "application_id")
    private String applicationId;
    @Column(name = "category")
    private String category;
    @Column(name = "condition")
    private String generalConditions;
    @Column(name = "sub_category")
    private String subCategory;
    @Column(name = "activity_id")
    private String activityId;
    @Column(name = "sector")
    private String sector;
    @Column(name = "sub_activity_id")
    private Integer subActivityId;
    @Column(name = "typeproposal")
    private String typeproposal;
    @Column(name = "heading")
    private String heading;
    @Column(name = "condition_heading")
    private String conditionHeading;
}
