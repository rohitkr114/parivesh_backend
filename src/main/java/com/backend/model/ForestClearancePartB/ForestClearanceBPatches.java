package com.backend.model.ForestClearancePartB;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "forest_clearance_part_b_patches", schema = "master")
public class ForestClearanceBPatches extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fc_form_a_part_b_id", nullable = false)
    @JsonIgnore
    private ForestClearanceBBasicDetails forestClearanceBBasicDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "kml_id", nullable = false)
    private DocumentDetails documentDetails_Kml;

    @Column(nullable = true, length = 1000)
    private String remarks;

    private Integer DocumentDetails_No_of_patches;

    @OneToMany(mappedBy = "forestClearanceBPatches", cascade = CascadeType.ALL)
    private List<ForestClearanceBPatchWiseDetails> forestClearanceBPatchWiseDetails = new ArrayList<>();

    @Column(nullable = false)
    private boolean is_active;

    @Column(nullable = false)
    private boolean is_deleted;

    ForestClearanceBPatches() {
        this.is_active = true;
        this.is_deleted = false;
    }

}
