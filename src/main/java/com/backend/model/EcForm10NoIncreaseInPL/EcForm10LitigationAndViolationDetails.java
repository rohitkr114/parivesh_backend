package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.EcForm9TransferOfEC.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * It consists of fields from point 5 to 7
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_legal_details", schema = "master")
public class EcForm10LitigationAndViolationDetails extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form10_project_details_id", nullable = true)
    @JsonIgnore
    private EcForm10ProjectDetails ecForm10ProjectDetails;

    /**
     * ########### @5 Government Order   ################
     */

    @Column(name = " violation_carried_out")
    private String violation_carried_out;


    @OneToMany(targetEntity = EcForm10GovernmentOrder.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_government_order_of_ec_id", referencedColumnName = "id")
    private Set<EcForm10GovernmentOrder> ecForm10GovernmentOrders = new HashSet<>();

    /**
     * ########### @6 Litigation Against Project   ################
     */


    @Column(name = "litigation_pending")
    private String litigation_pending;

    @OneToMany(targetEntity = EcForm10LitigationAgainstProject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_litigation_against_project_id", referencedColumnName = "id")
    private Set<EcForm10LitigationAgainstProject> ecForm10LitigationPending = new HashSet<>();


    /**
     * ########### @7 Voilation Involved   ################
     */


    @Column(name = "act_rule_regulation", length = 30)
    private String act_rule_regulation;

    @OneToMany(targetEntity = EcForm10ViolationInvolved.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_violation_involved_id", referencedColumnName = "id")
    private Set<EcForm10ViolationInvolved> ecForm10ViolationOfAct = new HashSet<>();


    @Column(name = "is_regulatory_authority")
    private String is_regulatory_authority;

    @OneToMany(targetEntity = EcForm10ViolationInvolvedDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_violation_involved_details_id", referencedColumnName = "id")
    private Set<EcForm10ViolationInvolvedDetails> ecForm10RegularAuthority = new HashSet<>();
}
