package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_addendum_of_transferor", schema = "master")
public class ECForm12AddendumOfTransferor extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_12_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm12 ecForm12;
    @Column(nullable = true, length = 5000)
    private String transferer_company_name;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "undertaking_by_transferor_copy_id", nullable = true)
    private DocumentDetails undertaking_by_transferor_copy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "addendum_copy_id", nullable = true)
    private DocumentDetails Addendum_copy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "noc_copy_id", nullable = true)
    private DocumentDetails noc_copy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ccr_copy_id", nullable = true)
    private DocumentDetails ccr_copy;

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    public ECForm12AddendumOfTransferor() {
        this.is_active = true;
        this.is_deleted = false;
    }
}
