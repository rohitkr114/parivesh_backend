package com.backend.model.EcForm9TransferOfEC;


import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Project Detail information which is sec1,2,3
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_form9_transfer_of_ec", schema = "master")
public class EcForm9TransferOfEC implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * ########### @1   Project detail ############
     */
    /*@Column(name = "single_window_number", nullable = true)
    private String project_sw_no;*/

    @Column(name = "project_name", nullable = true)
    private String project_name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caf_id", nullable = true)
    @JsonIgnore
    private CommonFormDetail commonFormDetail;

    @Column(name = "proposal_no", nullable = true)
    private String proposal_no;

    @Column(name = "proposal_no_for_apply", nullable = true)
    private String proposal_no_for_apply;

    @Column(name = "activity_id", nullable = true)
    private Integer activity_id;

    @Column(name = "subActivity_id", nullable = true)
    private Integer subActivity_id;

    @Column(name = "proposal_category", nullable = true)
    private String proposal_category;
    

    /*@OneToOne
    @JoinColumn(name = "ec_id", nullable = true)
    private EnvironmentClearence environmentClearence;
	
    @Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer ec_id;
    */


    /**
     * ########### @2  TRANSFERER DETAILS   ################
     */

    /*@Column(name = "application_made_by")
    private String application_made_by;*/

    @Column(name = " transferer_name", nullable = true)
    private String transferer_name;

    @Column(name = "transferer_street", nullable = true)
    private String transferer_street;

    @Column(name = "transferer_city", nullable = true)
    private String transferer_city;

    @Column(name = "transferer_district", nullable = true)
    private Integer transferer_district;

    @Column(name = "transferer_state", nullable = true)
    private Integer transferer_state;

    @Column(name = "transferer_pincode", nullable = true)
    private Integer transferer_pincode;

    @Column(name = "transferer_landmark", nullable = true)
    private String transferer_landmark;

    @Column(name = "transferer_email", nullable = true)
    private String transferer_email;

    @Column(name = "transferer_landline_no", nullable = true)
    private String transferer_landline_no;

    @Column(name = "transferer_mobile_no", nullable = true)
    private String transferer_mobile_no;

    @Column(name = "transferer_legal_status", nullable = true)
    private String transferer_legal_status;


    /**
     * ###########  @3  TRANSFEREE DETAILS   ################
     */

    @Column(name = "transferee_name", nullable = true)
    private String transferee_name;

    @Column(name = "transferee_street", nullable = true)
    private String transferee_street;

    @Column(name = "transferee_city", nullable = true)
    private String transferee_city;

    @Column(name = "transferee_district", nullable = true)
    private Integer transferee_district;

    @Column(name = "transferee_state", nullable = true)
    private Integer transferee_state;

    @Column(name = "transferee_pincode", nullable = true)
    private Integer transferee_pincode;

    @Column(name = "transferee_landmark", nullable = true)
    private String transferee_landmark;

    @Column(name = "transferee_email", nullable = true)
    private String transferee_email;

    @Column(name = "transferee_landline_no", nullable = true)
    private String transferee_landline_no;

    @Column(name = "transferee_mobile_no", nullable = true)
    private String transferee_mobile_no;

    @Column(name = "transferee_legal_status", nullable = true)
    private String transferee_legal_status;

    /**
     * ###########  APPLICANT DETAILS   ################
     */


    /*@Column(name = "applicant_name", length = 100)
    private String applicant_name;

    @Column(name = "applicant_designation", length = 100)
    private String applicant_designation;


    @Column(name = "applicant_street", length = 300)
    private String applicant_street;

    @Column(name = "applicant_city", length = 100)
    private String applicant_city;

    @Column(name = "applicant_district", length = 50)
    private Integer applicant_district;

    @Column(name = "applicant_state", length = 50)
    private Integer applicant_state;

    @Column(name = "applicant_pincode", length = 50)
    private Integer applicant_pincode;

    @Column(name = "applicant_landmark", length = 300)
    private String applicant_landmark;

    @Column(name = "applicant_email", length = 50)
    private String applicant_email;

    @Column(name = "applicant_landline_no", length = 300)
    private String applicant_landline_no;

    @Column(name = "applicant_mobile_no", length = 10)
    private String applicant_mobile_no;

    @Column(name = "applicant_i_agree", length = 10)
    private Boolean applicant_i_agree;
    
    */

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @OneToOne(mappedBy = "ecForm9TransferOfEC", cascade = CascadeType.ALL)
    private EcForm9LegalDetails ecForm9LegalDetails;

    @OneToOne(mappedBy = "ecForm9TransferOfEC", cascade = CascadeType.ALL)
    private EcForm9LocationOfProject ecForm9LocationOfProject;

    @OneToOne(mappedBy = "ecForm9TransferOfEC", cascade = CascadeType.ALL)
    private EcForm9Undertaking1 ecForm9Undertaking1;

    @OneToOne(mappedBy = "ecForm9TransferOfEC", cascade = CascadeType.ALL)
    private EcForm9ProposalDetails ecForm9ProposalDetails;


    public EcForm9TransferOfEC(Integer id, String proposal_no) {
        this.id = id;
        this.proposal_no = proposal_no;
    }


}
