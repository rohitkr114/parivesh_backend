package com.backend.model.FcSIR;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="fc_site_inspection_report",schema = "authority")
public class FcSiteInspectionReport extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="fc_id",nullable = false)
    private Integer fcId;

    @Column(name="ua_name")
    private String uaName;

    @Column(name="proposal_name",length = 1000)
    private String proposalName;

    @Column(name="state_code")
    private Integer stateCode;

    @Column(name="state_name",length = 100)
    private String stateName;

    @Column(name="district_code")
    private Integer districtCode;

    @Column(name="district_name",length = 100)
    private String districtName;

    @Column(name="tehsil",length = 100)
    private String tehsil;

    @Column(name="forest_block",length = 100)
    private String forestBlock;

    @Column(name="forest_range",length = 100)
    private String forestRange;

    @Column(name="forest_section",length = 100)
    private String forestSection;

    @Column(name="forest_beat",length = 100)
    private String forestBeat;

    @Column(name = "sir_start_date")
    private Date sirStartDate;

    @Column(name = "sir_end_date")
    private Date sirEndDate;

    @OneToMany(mappedBy = "fcSiteInspectionReport",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private List<FcSiteVisitDetails> fcSiteVisitDetails;

    @OneToOne(mappedBy = "fcSiteInspectionReport",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private FcSIRLegalStatusDetails fcSIRLegalStatusDetails;

    @OneToOne(mappedBy = "fcSiteInspectionReport",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted=false")
    private FcSIROtherDetails fcSIROtherDetails;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DepartmentApplication departmentApplication;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;


}
