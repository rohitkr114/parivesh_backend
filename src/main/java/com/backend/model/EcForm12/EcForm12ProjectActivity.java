package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ec_form_12_project_activity", schema = "master")
public class EcForm12ProjectActivity extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_12_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm12 ecForm12;

    @Column(nullable = true)
    private String moef_file_no;

    @Column(nullable = true)
    private Date prior_environmental_clearance_date;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_letter_copy_id", nullable = true)
    private DocumentDetails ec_letter_copy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "chronology_of_clearances_id", nullable = true)
    private DocumentDetails chronology_of_clearances;

    @Column(nullable = true)
    private Boolean is_earlier_ec_obtained;

    @OneToMany(mappedBy = "ecForm12ProjectActivity", cascade = CascadeType.ALL)
    @Where(clause = "is_delete ='false'")
    @EqualsAndHashCode.Exclude
    private List<EcForm12Obtained> ecForm12Obtaineds = new ArrayList<>();

    @Column(nullable = true)
    private Boolean is_delete;

    @Column(nullable = true)
    private Boolean is_active;

    public EcForm12ProjectActivity() {
        this.is_active = true;
        this.is_delete = false;
    }

    public EcForm12ProjectActivity(Integer id, EcForm12 ecForm12, String moef_file_no, Date prior_environmental_clearance_date,
                                   Boolean is_earlier_ec_obtained) {
        this.id = id;
        this.ecForm12 = ecForm12;
        this.moef_file_no = moef_file_no;
        this.prior_environmental_clearance_date = prior_environmental_clearance_date;
        this.is_earlier_ec_obtained = is_earlier_ec_obtained;
    }

    public EcForm12ProjectActivity(Integer id) {
        this.id = id;
    }

}
