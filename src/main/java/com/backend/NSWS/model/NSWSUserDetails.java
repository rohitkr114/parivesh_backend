package com.backend.NSWS.model;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "nsws_user_details", schema = "master")
public class NSWSUserDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer entityId;

    private String email;

    private String panNO;

    private String investorSWSId;

    private String approvalId;

    private Integer investorReqId;

    private String projectId;

    private String state_ut;

    private String departmentId;

    private String ministryId;

}
