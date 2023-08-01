package com.backend.model.FcFormEPartB;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartB;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "fc_form_e_part_b_ca_land", schema = "master")
public class FcFormEPartBCaLand extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fcFormEPartBBasicDetails_id", nullable = false)
    @JsonIgnore
    private ForestClearanceEBasicDetails fcFormEPartBBasicDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "kml_id", nullable = false)
    private DocumentDetails documentDetails_Kml;

    @Column(nullable = true, length = 1000)
    private String remarks;

    private Integer DocumentDetails_No_of_ca_land;

    @Column(nullable = true)
    private String cat_ca_land;

    @OneToMany(targetEntity = FcFormEPartBCaLandDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ca_land_id", referencedColumnName = "id")
    private List<FcFormEPartBCaLandDetails> fcFormEPartBCaLandDetails = new ArrayList<>();

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    FcFormEPartBCaLand() {
        this.is_active = true;
        this.is_deleted = false;
    }

}