package com.backend.model.EcForm9TransferOfEC;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form9_proposal_details", schema = "master")
public class EcForm9ProposalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form9_transfer_of_ec_id", nullable = true)
    @JsonIgnore
    private EcForm9TransferOfEC ecForm9TransferOfEC;

    /**
     * ########### @8 Proposal For Mining   ################
     */

    @Column(name = "proposal_for_mining")
    private String proposal_for_mining;


    /**
     * ########### @8 Proposal For Category   ################
     */

    @Column(name = "proposal_for_category")
    private String proposal_for_category;


    /**
     * ########### @10 Prior Environmental Clearance   ################
     */

    @Column(name = "moef_file_no", length = 100)
    private String moef_file_no;

    @Column(name = "issue_date")
    private String issue_date;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_ec_letter_copy_id", nullable = true)
    private DocumentDetails upload_ec_letter_copy;

    @Column(name = "is_ec_issued_under_provision_ex")
    private String is_ec_issued_under_provision_ex;

    /**
     * ########### @11 Proponent Details   ################
     */


    @Column(name = "mine_lease_no")
    private String mine_lease_no;

//    @Column(name = "production_capacity")
//    private String production_capacity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "form9_kml", nullable = true)
    private DocumentDetails form9_kml;

    @Column(name = "date_of_end_of_ec")
    private String date_of_end_of_ec;



    /**
     * ########### @12 Vesting Details   ################
     */

    @Column(name = "reference_no")
    private String reference_no;

    @Column(name = "date_of_allocation")
    private String date_of_allocation;

    @Column(name = "mine_lease_area_allocated")
    private String mine_lease_area;

    @Column(name = "end_date_of_mine_lease")
    private String end_date_of_min_lease_validity;

    @Column(name = "production_capacity_vesting")
    private String production_capacity_vesting_order;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_vesting_order", nullable = true)
    private DocumentDetails site_inspection_report;
    
    @OneToMany(targetEntity = EcForm9ProductionCapacity.class, cascade= CascadeType.ALL)
    @JoinColumn(name="ec_form9_proposal_details_id", referencedColumnName = "id")
    private List<EcForm9ProductionCapacity> productionCapacity= new ArrayList<EcForm9ProductionCapacity>();
    
}
