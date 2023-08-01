package com.backend.model.EcFactsheet;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ec_factsheet_other_details", schema = "master")
public class EcFactsheetOtherDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "is_exempted_public_hearing", nullable = true)
    private Boolean is_exempted_public_hearing;

    @Column(name = "public_hearing_issue", nullable = true, length = 500)
    private String public_hearing_issue;

    @Column(name = "court_pendency", nullable = true, length = 500)
    private String court_pendency;

    @Column(name = "project_benefit", nullable = true, length = 2000)
    private String project_benefit;

    @Column(name = "prev_eac_appraised_on", nullable = true)
    private Date prev_eac_appraised_on;

    @Column(name = "eac_final_recommendation_date", nullable = true)
    private Date eac_final_recommendation_date;

    @Column(name = "eac_final_recommendation", nullable = true, length = 100)
    private String eac_final_recommendation;

    @Column(name = "eac_secretary_name", nullable = true, length = 255)
    private String eac_secretary_name;

    @Column(name = "project_social_benefit", nullable = true, length = 2000)
    private String project_social_benefit;

    @Column(name = "project_financial_benefit", nullable = true, length = 2000)
    private String project_financial_benefit;

    @Lob
    @Column(name = "public_hearing_exempted_reason", nullable = true)
    private String public_hearing_exempted_reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_factsheet_id", nullable = true)
    @JsonIgnore
    private EcFactsheet ecFactsheet;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    public EcFactsheetOtherDetails() {
        this.is_active = true;
        this.is_deleted = false;
    }


}
