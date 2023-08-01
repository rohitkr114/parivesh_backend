package com.backend.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

public interface ProjectProposalDetailsDto {

    Integer getCreated_by();

    Date getCreated_on();

    Integer getUpdated_by();

    Date getUpdated_on();

    Integer getApplication_id();

    String getProject_name();

    String getProposal_no();

    Integer getProposal_id();

    Integer getWork_group_id();

    String getWorkgroup_name();

    String getLast_status();

    String getLast_visible_status();

    Date getLast_submission_date();

    Integer getClearance_id();

    String getClearance_name();
}
