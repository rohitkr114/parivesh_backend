package com.backend.dto;

import lombok.Data;

import java.util.Date;

public interface ProposalForRemovalDto {

    Integer getApplication_id();

    String getProposal_no();

    Integer getProposal_id();

    String getLast_status();

    String getLast_visible_status();

    Date getCreated_on();

    Date getUpdated_on();




}
