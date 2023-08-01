package com.backend.NSWS.requestbody;

import lombok.Data;

@Data
public class PingRequestBody {

    private String name_of_Entity;

    private String pan_no;

    private String organisation_name_as_per_pan;

    private String address;

    private String email;

    private String mobile;

    private Integer state_ut;

    private Integer district;

    private String pincode;

    private Boolean is_organisation;

    private String org_name;

    private Integer org_state_ut;

    private Integer org_district;

    private String org_pincode;

    private String org_address;

    private Boolean is_aadharVerified;

    private String investorSWSId;

    private String approvalId;

    private Integer investorReqId;

    private String projectId;


}
