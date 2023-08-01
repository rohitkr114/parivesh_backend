package com.backend.NSWS.external.request;

import lombok.Data;

@Data
public class RedirectUrlRequest {

    private String departmentId;

    private String licenseId;

    private String redirectionUrl;

    private String stateId;

    private String swsId;

    private String ministryId;

    private String projectId;

    private Integer InvestorReqId;
}
