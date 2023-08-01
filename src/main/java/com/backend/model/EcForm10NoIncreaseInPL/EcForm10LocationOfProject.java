package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * It consists of fields from point 4
 */
@Data
@Entity
@Table(name = "ec_form10_location_of_project", schema = "master")
public class EcForm10LocationOfProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * ###########  @4 Location Of Project   ################
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form10_project_details_id", nullable = true)
    @JsonIgnore
    private EcForm10ProjectDetails ecForm10ProjectDetails;

    @OneToOne
    @JoinColumn(name = "form10_kml")
    private DocumentDetails form10_kml;

    @Column(name = "organization_plot", length = 100)
    private String organization_plot;

    @Column(name = "organization_village", length = 100)
    private String organization_village;

    @Column(name = "organization_sub_district", length = 100)
    private String organization_sub_district;

    @Column(name = "organization_district", length = 100)
    private String organization_district;

    @Column(name = "organization_state", length = 100)
    private String organization_state;

    @Column(name = "organization_pincode", length = 20)
    private String organization_pincode;

    @Column(name = "organization_latitudes_degree", length = 53)
    private Float organization_latitudes_degree;

    @Column(name = "organization_latitudes_minute", length = 53)
    private Float organization_latitudes_minute;

    @Column(name = "organization_latitudes_second", length = 53)
    private Float organization_latitudes_second;

    @Column(name = "organization_longitudes_minute", length = 53)
    private Float organization_longitudes_minute;

    @Column(name = "organization_longitudes_second", length = 53)
    private Float organization_longitudes_second;

    @Column(name = "organization_longitudes_degree", length = 53)
    private Float organization_longitudes_degree;

    @OneToMany(targetEntity = EcForm10ToposheetNo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form10_location_of_project_id", referencedColumnName = "id")
    private Set<EcForm10ToposheetNo> ecForm10_toposheet_Nos = new HashSet<>();

    @Column(name = "falling_border_in_states", length = 53)
    private boolean falling_border_in_states;

    @Column(name = "international_border_distance", length = 53)
    private String international_border_distance;

    @Column(name = "shape_of_project", length = 53)
    private String shape_of_project;

}
