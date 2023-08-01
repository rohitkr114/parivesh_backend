package com.backend.model.EcForm7;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ec_form_7_activity_status", schema = "master")
public class EcForm7ActivityStatus extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ec_form_7_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm7 ecForm7;

    @Column(nullable = true)
    private String status_of_implementation;

    @Column(nullable = true)
    private String reference_number_consent_obtained;

    @Column(nullable = true)
    private Date latest_consent_issued_date;

    @Column(nullable = true)
    private Date latest_consent_validity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "latest_consent_order_copy_id", nullable = true)
    private DocumentDetails latest_consent_order_copy;

    @Column(nullable = true)
    private String reference_number_consent_establishment;

    @Column(nullable = true)
    private Date consent_issued_date;

    @Column(nullable = true)
    private Date consent_validity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "consent_order_including_renewal_id", nullable = true)
    private DocumentDetails consent_order_including_renewal;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "brief_note_status_of_implementation_copy_id", nullable = true)
    private DocumentDetails brief_note_status_of_implementation_copy;

    @Column(nullable = true, length = 500)
    private String reason;

    @OneToMany(targetEntity = EcForm7EcStatus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form_7_ec_status_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Set<EcForm7EcStatus> ecForm7EcStatus = new HashSet<>();

    @Column(nullable = true)
    private Boolean is_delete;

    @Column(nullable = true)
    private Boolean is_active;

    public EcForm7ActivityStatus() {
        this.is_active = true;
        this.is_delete = false;
    }

    public EcForm7ActivityStatus(Integer id) {
        this.id = id;
    }

}
