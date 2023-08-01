package com.backend.dto.EcForm8;

import javax.persistence.Column;

public interface EcForm8dto {

  Integer getId();

  Integer getEc_id();
  String getProject_sw_no();
  String getProject_name();
  String getProposal_no();
  String getTransferer_name();
  String getTransferer_street();
  String getTransferer_city();
  Integer getTransferer_district();
  Integer getTransferer_state();
  Integer  getTransferer_pincode();
  String getTransferer_landmark();
  String getTransferer_email();
  String getTransferer_landline_no();
  String getTransferer_mobile_no();
  String getTransferer_legal_status();
  String getTransferee_name();
  String getTransferee_street();
  String getTransferee_city();
  Integer  getTransferee_district();
  Integer getTransferee_state();
  Integer getTransferee_pincode();
  String getTransferee_landmark();
  String getTransferee_email();
  String getTransferee_landline_no();
  String getTransferee_mobile_no();
  String getTransferee_legal_status();
  String getApplicant_name();
  String getApplicant_designation();
  String getApplicant_street();
  String getApplicant_city();
  Integer getApplicant_district();
  Integer getApplicant_state();
  Integer getApplicant_pincode();
  String getApplicant_landmark();
  String getApplicant_email();
  String getApplicant_landline_no();
  String getApplicant_mobile_no();
  String getApplication_made_by();
  Boolean getIs_multiple_items_involved();
  Boolean getIs_proposed_required();
  Integer getMajor_activity_id();
  Integer getMajor_sub_activity_id();
  String getProject_category();
  String getEc_proposal_no();
}
