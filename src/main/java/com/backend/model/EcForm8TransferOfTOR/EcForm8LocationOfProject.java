package com.backend.model.EcForm8TransferOfTOR;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Location of the project
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_form8_location_of_project", schema = "master")
public class EcForm8LocationOfProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_form_8_document_id", nullable = true)
    @JsonIgnore
    private EcForm8TransferOfTOR ecForm8TransferOfTOR;

    @OneToOne
    @JoinColumn(name = "form8_kml")
    private DocumentDetails form8_kml;

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

    @Column(nullable = true)
    private String organization_latitudes_ne_from;

    @Column(nullable = true)
    private String organization_latitudes_ne_to;

    @Column(nullable = true)
    private String organization_longitudes_sw_from;

    @Column(nullable = true)
    private String organization_longitudes_sw_to;

    @OneToMany(targetEntity = EcForm8CafKML.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_form_8_id", referencedColumnName = "id")
    @Where(clause = "is_deleted ='false'")
    private List<EcForm8CafKML> ecForm8CafKML = new ArrayList<>();
}
