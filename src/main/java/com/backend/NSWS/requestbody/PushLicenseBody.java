package com.backend.NSWS.requestbody;

import lombok.Data;

@Data
public class PushLicenseBody {
    private String licenseId;

    private Integer licenseVer;

    private String swsId;

    private Integer investorReqId;

    private String licenseReqDate;

    private String ministeryId;

    private String departmentId;
}
