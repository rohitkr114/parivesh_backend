package com.backend.model.EcForm2;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="ec_form_2_project_details",schema = "master")
public class EcForm2ProjectDetails extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="ec_proposal_no", length=255)
    private String ecProposalNo;

    @Column(name = "project_name",columnDefinition = "text")
    private String projectName;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "project_category")
    private String projectCategory;

    @Column(name="is_multiple_item_involved")
    private Boolean isMultipleItemInvolved;

    @Column(name="major_activity_id",nullable = false)
    private Integer majorActivityId;

    @Column(name = "major_sub_activity_id",nullable = true)
    private Integer majorSubActivityId;

    @Column(name="is_proposed_required")
    private Boolean isProposedRequired;

    @Column(name="schedule_name")
    private String scheduleName;

    @Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer ec_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_id", nullable = true)
    @JsonIgnore
    private EcPartC ecPartc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_part_a_id", nullable = true)
    @JsonIgnore
    private EnvironmentClearence environmentClearence;

    @Column(name = "ec_part_a_id", nullable = true, updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer ecPartAId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="house_no")
    private String houseNo;

    private String village;

    private Integer district;

    private Integer state;

    private String pinCode;

    private String landmark;

    private String email;

    private String landline;

    private String mobile;

    @Column(name="legal_status_of_company")
    private String legalStatusOfCompany;

    private String organization_district;

    private Double organization_latitudes_ne_from;

    private Double organization_latitudes_ne_to;

    private Double organization_longitudes_sw_from;

    private Double organization_longitudes_sw_to;

    private String organization_pincode;

    private String organization_plot;

    private String organization_state;

    private String organization_sub_district;

    private String organization_village;

    @OneToMany(mappedBy="ecForm2",cascade = CascadeType.ALL)
    @Where(clause="is_deleted=false")
    private List<EcForm2ProjectActivityDetails> ecForm2ProjectActivityDetails=new ArrayList<EcForm2ProjectActivityDetails>();

    @OneToMany(targetEntity = EcForm2CafKML.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form2_id",referencedColumnName = "id")
    @Where(clause = "is_deleted =false")
    private Set<EcForm2CafKML> ecForm2CafKML=new HashSet<>();

    @OneToOne(mappedBy = "ecForm2", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted =false")
    private EcForm2ProjectImplementationStatus ecForm2ProjectImplementationStatus;

    @OneToMany(mappedBy = "ecForm2",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted =false")
    private List<EcForm2ImplementationStatus> ecForm2ImplementationStatus= new ArrayList<EcForm2ImplementationStatus>();

    @OneToMany(mappedBy = "ecForm2",cascade = CascadeType.ALL)
    @Where(clause = "is_deleted =false")
    private List<EcForm2CorrigendumDescription> ecForm2CorrigendumDescriptions=new ArrayList<EcForm2CorrigendumDescription>();

    @OneToOne(mappedBy = "ecForm2", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted =false")
    private EcForm2EnclosureDetails ecForm2EnclosureDetails;

    @OneToOne(mappedBy = "ecForm2", cascade = CascadeType.ALL)
    @Where(clause = "is_deleted =false")
    private EcForm2Undertaking ecForm2Undertaking;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caf_id", nullable = true)
    private CommonFormDetail commonFormDetail;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive=true;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted=false;


}
