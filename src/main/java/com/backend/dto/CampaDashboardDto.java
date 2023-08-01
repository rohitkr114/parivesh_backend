package com.backend.dto;

import java.util.Date;

public interface CampaDashboardDto {

    Integer getCampa_id();

    Integer getCampa_created_by();

    Date getCampa_created_on();

    Integer getCampa_updated_by();

    Date getCampa_updated_on();

    Integer getDemand_raised_to();

    String getProposal_no();

    String getCampa_address();

    String getCampa_agency_name();

    String getDistrict_code();

    String getEmail_id();

    Integer getFc_id();

    String getMobile();

    String getState_code();

    String getTelephone();

//    Double getOther_charges();

    String getOther();

    Double getAdditional_charges();

    Double getAdditional_compensatory_afforestation();

    Double getAfforestation_safety_zone();

    Double getCatchment_area();

    Double getCompensatory_afforestation();

//    Boolean getIs_other_charges();

//    Boolean getIs_proposal_applicable();

    Double getNet_present_value();

    Double getPenal_compensatory_afforestation();

    String getRemarks();

    Double getSoil_conservation();

    Double getTotal();

    Double getWildlife_conservation();

    Double getPenal_net_present_value();

    String getStatus();


}
