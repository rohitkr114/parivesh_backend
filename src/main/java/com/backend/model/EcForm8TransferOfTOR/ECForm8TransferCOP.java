package com.backend.model.EcForm8TransferOfTOR;

import com.backend.audit.Auditable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Form 8: Category of the project/Activity
 */
@Data
@Entity
@Table(name = "ec_form8_category_of_project", schema = "master")
public class ECForm8TransferCOP extends Auditable<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ec_form_8_cat_activity_id", nullable = true)
  @JsonIgnore
  private EcForm8TransferOfTOR ecForm8TransferOfTOR;

  @Column(name = "is_multiple_items_involved_in_proposal")
  private boolean is_multiple_items_involved_in_proposal;

  @OneToMany(targetEntity = EcForm8Activities.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "ec_form8_category_of_project_id", referencedColumnName = "id")
  private Set<EcForm8Activities> ecActivities = new HashSet<>();

  @Column(name = "project_category")
  private String project_category;

  @Column(name = "is_proposed_required")
  private Boolean is_proposed_required;

  @Column(name = "central_application_reason")
  private String central_application_reason;
}