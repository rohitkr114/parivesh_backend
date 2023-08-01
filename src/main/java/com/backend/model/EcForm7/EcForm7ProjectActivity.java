package com.backend.model.EcForm7;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ec_form_7_project_activity", schema = "master")
public class EcForm7ProjectActivity extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_7_id", nullable = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private EcForm7 ecForm7;

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

    @OneToMany(mappedBy = "ecForm7ProjectActivity", cascade = CascadeType.ALL)
    @Where(clause = "is_delete ='false'")
    @EqualsAndHashCode.Exclude
    private List<EcForm7Obtained> ecForm7Obtaineds = new ArrayList<>();

    @Column(nullable = true)
    private Boolean is_delete;

    @Column(nullable = true)
    private Boolean is_active;

    public EcForm7ProjectActivity() {
        this.is_active = true;
        this.is_delete = false;
    }

    public EcForm7ProjectActivity(Integer id, EcForm7 ecForm7, String moef_file_no, Date prior_environmental_clearance_date,
                                  Boolean is_earlier_ec_obtained) {
        this.id = id;
        this.ecForm7 = ecForm7;
        this.moef_file_no = moef_file_no;
        this.prior_environmental_clearance_date = prior_environmental_clearance_date;
        this.is_earlier_ec_obtained = is_earlier_ec_obtained;
    }

    public EcForm7ProjectActivity(Integer id) {
        this.id = id;
    }

}
