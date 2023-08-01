package com.backend.model.EcForm9TransferOfEC;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "ec_form9_location_of_project", schema = "master")
public class EcForm9LocationOfProject  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * ###########  @4 Location Of Project   ################
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form9_transfer_of_ec_id", nullable = true)
    @JsonIgnore
    private EcForm9TransferOfEC ecForm9TransferOfEC;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "form9_kml")
    private DocumentDetails form9_kml;

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

    @OneToMany(targetEntity = EcForm9ToposheetNo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form9_location_of_project_id", referencedColumnName = "id")
    private Set<EcForm9ToposheetNo> ecForm9_toposheet_Nos = new HashSet<>();

    @Column(name = "falling_border_in_states", length = 53)
    private boolean falling_border_in_states;

    @Column(name = "international_border_distance", length = 53)
    private String international_border_distance;

    @Column(name = "shape_of_project", length = 53)
    private String shape_of_project;



}
