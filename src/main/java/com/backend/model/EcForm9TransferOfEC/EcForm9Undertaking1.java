package com.backend.model.EcForm9TransferOfEC;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ec_form9_undertaking_1", schema = "master")

public class EcForm9Undertaking1 extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form9_undertaking_id", nullable = true)
    @JsonIgnore
    private EcForm9TransferOfEC ecForm9TransferOfEC;

    /**
     * ###########  @13 Copy Of Undertaking   ################
     */

    @Column(name = "i_agree", nullable = true)
    private boolean i_agree;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_undertaking", nullable = true)
    private DocumentDetails copy_of_undertaking;

    /**
     * ###########  @14  Undertaking   ################
     */

    @Column(name = "undertaking_i_agree", nullable = true)
    private boolean undertaking_i_agree;

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

    public EcForm9Undertaking1() {
        this.is_active = true;
        this.is_delete = false;
    }

    public EcForm9Undertaking1(Integer id, boolean i_agree, DocumentDetails copy_of_undertaking, String undertaking_person_name,
                              String undertaking_person_designation, String undertaking_person_company, String undertaking_person_address,
                               Date undertaking_date, Boolean is_active, Boolean is_delete) {
        this.id = id;
        this.i_agree = i_agree;
        this.copy_of_undertaking=copy_of_undertaking;
        this.undertaking_person_name = undertaking_person_name;
        this.undertaking_person_designation = undertaking_person_designation;
        this.undertaking_person_company = undertaking_person_company;
        this.undertaking_person_address = undertaking_person_address;
        // this.undertaking_person_esign = undertaking_person_esign;
        this.undertaking_date = undertaking_date;
        this.is_active = is_active;
        this.is_delete = is_delete;

    }
}
