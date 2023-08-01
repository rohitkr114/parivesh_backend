package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * It consists of fields from point 1 to 3
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_project_details", schema = "master")
public class EcForm10ProjectDetails implements Clearence{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * ########### @1   Project detail ############
     */

    @Column(name = "project_name", nullable = true,length = 500)
    private String  project_name;

    @Column(name = "project_proposal_for", nullable = true,length = 100)
    private String  project_proposal_for;

    @Column(name = "expansion_under_seven", nullable = true)
    private String  expansion_under_seven;

    @Column(name = "project_sw_no", nullable = true)
    private String project_sw_no;

    @Column(name = "nature_of_project", nullable = true,length = 200)
    private String nature_of_project;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caf_id", nullable = true)
    @JsonIgnore
    private CommonFormDetail commonFormDetail;

    @Column(name = "proposal_no", nullable = true)
    private String proposal_no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_id", nullable = true)
    @JsonIgnore
    private EnvironmentClearence environmentClearence;


    @Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer ec_id;


    /**
     * ########### @2  ORGANIZATION DETAILS   ################
     */

    @Column(name = " name", nullable = true)
    private String name;

    @Column(name = "street", nullable = true)
    private String street;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "district", nullable = true)
    private String district;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "pincode", nullable = true)
    private Integer pincode;

    @Column(name = "landmark", nullable = true)
    private String landmark;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "landline_no", nullable = true)
    private String landline_no;

    @Column(name = "mobile_no", nullable = true)
    private String mobile_no;

    @Column(name = "legal_status", nullable = true)
    private String legal_status;

    /**
     * ###########  APPLICANT DETAILS   ################
     */


    @Column(name = "applicant_name")
    private String applicant_name;

    @Column(name = "applicant_designation", length = 50)
    private String applicant_designation;

    @Column(name = "applicant_street")
    private String applicant_street;

    @Column(name = "applicant_city")
    private String applicant_city;

    @Column(name = "applicant_district")
    private String applicant_district;

    @Column(name = "applicant_state")
    private String applicant_state;

    @Column(name = "applicant_pincode")
    private Integer applicant_pincode;

    @Column(name = "applicant_landmark")
    private String applicant_landmark;

    @Column(name = "applicant_email")
    private String applicant_email;

    @Column(name = "applicant_landline_no")
    private String applicant_landline_no;

    @Column(name = "applicant_mobile_no")
    private String applicant_mobile_no;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    /*
    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10LitigationAndViolationDetails ecForm10LitigationAndViolationDetails;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10LocationOfProject ecForm10LocationOfProject;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10BasicInformation ecForm10BasicInformation;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10ProductDetails ecForm10ProductDetails;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10EmissionGeneration ecForm10EmissionGeneration;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10HazardousWasteGeneration ecForm10HazardousWasteGeneration;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10AdditionalDocument ecForm10AdditionalDocument;

    @OneToOne(mappedBy = "ecForm10ProjectDetails")
    private EcForm10Undertaking ecForm10Undertaking;    
    */
    
    public EcForm10ProjectDetails(Integer id, String proposal_no) {
        this.id = id;
        this.proposal_no = proposal_no;
    }
}
