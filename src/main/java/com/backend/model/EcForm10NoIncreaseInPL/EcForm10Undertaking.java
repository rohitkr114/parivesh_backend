package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ec_form10_undertaking", schema = "master")
public class EcForm10Undertaking  extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form10_undertaking_id", nullable = true)
    @JsonIgnore
    private EcForm10ProjectDetails ecForm10ProjectDetails;
    */
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form10_basic_information_id", nullable = true)
	@JsonIgnore
	private EcForm10BasicInformation ecForm10BasicInformation;

    @Column(name = "i_agree", nullable = true)
    private boolean i_agree;

    @Column(name = "undertaking_person_name", length = 100, nullable = true)
    private String undertaking_person_name;

    @Column(name = "undertaking_person_designation", length = 100, nullable = true)
    private String undertaking_person_designation;

    @Column(name = "undertaking_person_company", length = 255, nullable = true)
    private String undertaking_person_company;

    @Column(name = "undertaking_person_address", length = 255, nullable = true)
    private String undertaking_person_address;

    @Column(name = "undertaking_date", nullable = true)
    private Date undertaking_date;

    @Transient
    private Date submission_date;

    private Boolean is_active;

    private Boolean is_delete;
    
	@Column(name = "identification_no", nullable = true)
	private String identification_no;

    public EcForm10Undertaking() {
        this.is_active = true;
        this.is_delete = false;
    }

    public EcForm10Undertaking(Integer id, boolean i_agree, String undertaking_person_name,
                              String undertaking_person_designation, String undertaking_person_company, String undertaking_person_address,
                              String undertaking_person_esign, Date undertaking_date, Boolean is_active, Boolean is_delete) {
        this.id = id;
        this.i_agree = i_agree;
        this.undertaking_person_name = undertaking_person_name;
        this.undertaking_person_designation = undertaking_person_designation;
        this.undertaking_person_company = undertaking_person_company;
        this.undertaking_person_address = undertaking_person_address;
        this.undertaking_date = undertaking_date;
        this.is_active = is_active;
        this.is_delete = is_delete;
    }

}
