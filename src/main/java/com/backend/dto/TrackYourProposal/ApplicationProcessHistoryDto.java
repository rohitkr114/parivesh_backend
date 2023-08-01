package com.backend.dto.TrackYourProposal;

import java.util.Date;

public interface ApplicationProcessHistoryDto {

    String getId();

    String getApplication_id();

    String getProposal_no();

    String getWorkgroup_id();

    String getProcess_id();

    String getProcess_step_mapping_id();

    String getVersion();

    String getCurrent_step_id();

    Date getStart_date();

    Date getEnd_date();

    String getIs_current_step();

    String getStatus();

    String getRemarks();

    String getEntityid();

    String getAbbriviation();

    String getName();

    Integer getProject_id();
}
