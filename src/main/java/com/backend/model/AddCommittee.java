package com.backend.model;

import lombok.Data;

@Data
public class AddCommittee {

    private  Integer id ;

    private String address;

    private String districtCode;

    private String emailId;

    private String mobileNumber;

    private String name;

    private String password;

    private String pin;

    private String userType;

    private String securityAnswer;

    private String securityQuestion;

    private Integer selectedSector;

    private Integer selectedOffice;

    private Integer selectedRole;

    private Integer stateId;

    private String ipAddress;

    private Integer designationId;

    private Integer officeId;

    private String committeeType;

    private Integer sectorId;

    private Integer workgroupId;

}
