package com.backend.model.ForestClearanceE;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "fc_form_e", schema = "master")
public class FcFormE extends Auditable<Integer> implements Clearence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = true)
    private Integer fc_id;

    @Transient
    @JsonProperty(access = Access.READ_ONLY)
    private ProponentApplications proponentApplications;

    @Column(nullable = true)
    private Integer state;

    @Column(nullable = true, length = 100)
    private String application_for;

    @Column(nullable = true)
    private String rediversion_sought_by;

    @Column(nullable = true)
    private String purpose_of_rediversion;

    @Column(nullable = true)
    private String justification;

    @Column(nullable = true)
    private String proposal_no;

    @Column(nullable = true)
    private Boolean is_rediversion_proposed;

    @Column(name = "rediversion_category")
    private String rediversionCategory;

    @OneToOne(targetEntity = CommonFormDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "caf_id", nullable = true)
    private CommonFormDetail commonFormDetail;

    @OneToOne(mappedBy = "fcFormE")
    private FcFormEProposedLand fcFormEProposedLand;

    @OneToOne(mappedBy = "fcFormE")
    private FcFormEOtherDetails fcFormEOtherDetails;

    @OneToOne(mappedBy = "fcFormE")
    private FcFormEUndertaking fcFormEUndertaking;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_noc_copy_id", nullable = true)
    private DocumentDetails upload_noc_copy;

//	@OneToMany(mappedBy = "fcFormE", cascade = CascadeType.ALL)
//	@Fetch(FetchMode.SELECT)
//	@Where(clause = "is_delete='false'")
//	List<FcFormEApprovalDetails> fcFormEApprovalDetails= new ArrayList<>();

    @OneToMany(mappedBy = "fcFormE", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "is_delete='false'")
    List<FcFormEPriorProposal> fcFormEPriorProposals = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormE", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "is_delete='false'")
    List<FcFormECompliance> fcFormECompliances = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormE", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "is_delete='false'")
    List<FcFormEPatchDetails> fcFormEPatchDetails = new ArrayList<>();

    @OneToMany(mappedBy = "fcFormE", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "is_delete='false'")
    List<FcFormEKmls> fcFormEKmls = new ArrayList<>();

    @Column(nullable = true)
    private Integer parent_id;

    @Column(nullable = true)
    private Boolean is_active;

    @Column(nullable = true)
    private Boolean is_delete;

    public FcFormE() {
        this.is_active = true;
        this.is_delete = false;
    }

    public FcFormE(Integer id) {
        this.id = id;
    }


    public FcFormE(Integer id, Integer state, String rediversion_sought_by, String purpose_of_rediversion,
                   String justification, String proposal_no) {
        this.id = id;
        this.state = state;
        this.rediversion_sought_by = rediversion_sought_by;
        this.purpose_of_rediversion = purpose_of_rediversion;
        this.justification = justification;
        this.proposal_no = proposal_no;
    }

    public FcFormE(Integer id, Integer state, String application_for, String rediversion_sought_by, String purpose_of_rediversion,
                   String justification, String proposal_no, Boolean is_rediversion_proposed, String rediversionCategory) {
        this.id = id;
        this.state = state;
        this.application_for = application_for;
        this.rediversion_sought_by = rediversion_sought_by;
        this.purpose_of_rediversion = purpose_of_rediversion;
        this.justification = justification;
        this.proposal_no = proposal_no;
        this.is_rediversion_proposed = is_rediversion_proposed;
        this.rediversionCategory = rediversionCategory;
    }

}
