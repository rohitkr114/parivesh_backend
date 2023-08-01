package com.backend.model.EcForm9TransferOfEC;


import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form9_legal_details", schema = "master")
public class EcForm9LegalDetails extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form9_transfer_of_ec_id", nullable = true)
    @JsonIgnore
    private EcForm9TransferOfEC ecForm9TransferOfEC;

    /**
     * ########### @5 Government Order   ################
     */

    @Column(name = " violation_carried_out")
    private String violation_carried_out;


    @OneToMany(targetEntity = EcForm9GovernmentOrder.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form9_government_order_of_ec_id", referencedColumnName = "id")
    private Set<EcForm9GovernmentOrder> ecForm9GovernmentOrders = new HashSet<>();

    /**
     * ########### @6 Litigation Against Project   ################
     */


    @Column(name = "litigation_pending")
    private String litigation_pending;

    @OneToMany(targetEntity = EcForm9LitigationAgainstProject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form9_litigation_against_project_id", referencedColumnName = "id")
    private Set<EcForm9LitigationAgainstProject> ecForm9LitigationPending = new HashSet<>();


    /**
     * ########### @7 Voilation Involved   ################
     */


    @Column(name = "act_rule_regulation", length = 30)
    private String act_rule_regulation;

    @OneToMany(targetEntity = EcForm9ViolationInvolved.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form9_violation_involved_id", referencedColumnName = "id")
    private Set<EcForm9ViolationInvolved> ecForm9ViolationOfAct = new HashSet<>();


    @OneToMany(targetEntity = EcForm9ViolationInvolvedDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form9_violation_involved_details_id", referencedColumnName = "id")
    private Set<EcForm9ViolationInvolvedDetails> ecForm9RegularAuthority = new HashSet<>();


}
