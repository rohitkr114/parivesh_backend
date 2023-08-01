package com.backend.model.EcForm13;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="ec_form13_undertaking",schema = "master")
public class EcForm13Undertaking extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "is_undertaking_checked", nullable = true)
    private Boolean is_undertaking_checked;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "designation", nullable = true)
    private String designation;

    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "esign", nullable = true)
    private String esign;

    @Column(name = "date", nullable = true)
    private Date date;

    @Transient
    private Date submission_date;

    @Column(name = "identification_no", nullable = true)
    private String identification_no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ec_form13_id",nullable = false)
    @JsonIgnore
    private EcForm13ProjectDetails ecForm13;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;


}
