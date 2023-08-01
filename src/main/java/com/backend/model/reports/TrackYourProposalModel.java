package com.backend.model.reports;

import lombok.Data;

import java.util.Date;

public interface TrackYourProposalModel {

    Integer getId();
    String getProposal_id();
    String  getProposalNo();
    String getCAFNumber();
    String getProject_id();
    String  getProposalStatus();
    String getClearanceType();
    String getProjectName();
    String getSingleWindowNumber();
    String getNameOfUserAgency();
    String getCategory();
    String getState();
    String getLast_submission_date();
    String getMoefccFileNumber();
    String getCertificateUrl();
    String getProposalType();
    String getIssuing_authority();

    String getDateOfSubmission();

    String getOther_property();

    Integer getForm_id();

    Integer getApplication_id();
    
    String getForest_area();
    String getSector();
    
    String getApp_updated_on();
}
