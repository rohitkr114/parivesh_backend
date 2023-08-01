package com.backend.model.ForestClearanceFormF;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fc_form_f",schema = "master")
public class FcFormF extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String proposalNo;

    private String nameOfProject;

    private String natureOfTransfer;

    private String transferorName;

    private String transferorHouseNo;

    private String transferorVillage;

    private Integer transferorDistrict;

    private Integer transferorState;

    private String transferorPincode;

    private String transferorLandmark;

    private String transferorEmail;

    private String transferorLandline;

    private String transferorMobile;

    private String transferorLegalStatusOfCompany;

    private String transfereeName;

    private String transfereeHouseNo;

    private String transfereeVillage;

    private Integer transfereeDistrict;

    private Integer transfereeState;

    private String transfereePincode;

    private String transfereeLandmark;

    private String transfereeEmail;

    private String transfereeLandline;

    private String transfereeMobile;

    private String transfereeLegalStatusOfCompany;

//    private String applicantName;
//
//    private String applicantHouseNo;
//
//    private String applicantVillage;
//
//    private Integer applicantDistrict;
//
//    private Integer applicantState;
//
//    private String applicantPincode;
//
//    private String applicantLandmark;
//
//    private String applicantEmail;
//
//    private String applicantLandline;
//
//    private String applicantMobile;
//
//    private String applicantLegalStatusOfCompany;


}
