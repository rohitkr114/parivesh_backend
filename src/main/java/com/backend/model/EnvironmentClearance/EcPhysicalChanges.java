package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_physical_changes", schema = "master")
public class EcPhysicalChanges extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ec_partb_id", nullable = true)
    @JsonIgnore
    private EcPartB ecPartB;

    private boolean change_in_land_use;


    @Column(nullable = true, length = 50)
    private Double total_current_land_use_area;


    @Column(nullable = true, length = 50)
    private Double green_belt_land_requirement;

    @Column(nullable = true, length = 50)
    private Double total_proposed_land_use;


    @Column(nullable = true, length = 50)
    private Double green_belt_existing_land_req;

    @Column(nullable = true, length = 50)
    private Double green_belt_proposed_land_req;

    @Column(nullable = true, length = 50)
    private Double green_belt_total_land_req;


    @Column(nullable = true, length = 50)
    private Double total_existing_land_req;

    @Column(nullable = true, length = 50)
    private Double total_proposed_land_req;

    @Column(nullable = true, length = 50)
    private Double toal_land_req;

    private boolean demolition_work_involved;


    @Column(nullable = true, length = 50)
    private Double demolition_work_no_total;
    @Column(nullable = true, length = 50)
    private Double demolition_work_area_total;


    private boolean temporary_use_construction_work;


    private boolean existing_vegetation_clearance;

    @Column(nullable = true, length = 10)
    private Integer no_of_trees;

    @Column(nullable = true, length = 50)
    private Double plantation_area_to_be_cleared;

    @Column(nullable = true, length = 50)
    private Integer trees_proposed_for_plantation;

    @Column(nullable = true, length = 300)
    private String vegetation_other_information;

    @Column(nullable = true, length = 100)
    private Boolean loss_of_native_spieces;

    @Column(nullable = true, length = 300)
    private String loss_of_native_spieces_dtls;

    private String loss_of_native_spieces_othr_info;

    private boolean linear_structure_proposed_for_diversion;

    @Column(nullable = true, length = 300)
    private String linear_structure_proposed_dtls;

    @Column(nullable = true, length = 50)
    private Double linear_structure_length;

    @Column(nullable = true, length = 50)
    private Double area_covered_linear_structure;

    @Column(nullable = true, length = 300)
    private String linear_structure_other_information;

    private boolean existing_transportation_change;

    @Column(nullable = true, length = 50)
    private Double existing_transportation_length;

    @Column(nullable = true, length = 300)
    private String alternative_arrangement_proposed;

    @Column(nullable = true, length = 300)
    private String existing_transportation_other_info;

    private boolean water_bodies_change;

    @Column(nullable = true, length = 300)
    private String water_body_dtls;

    @Column(nullable = true, length = 50)
    private Double water_bodies_proposed_area;

    @Column(nullable = true, length = 255)
    private String water_bodies_proposed_area_unit;

    @Column(nullable = true, length = 50)
    private Integer no_of_water_courses;

    @Column(nullable = true, length = 50)
    private Double proposed_water_course_length;

    @Column(nullable = true, length = 255)
    private String proposed_water_course_length_unit;

    @Column(nullable = true, length = 300)
    private String water_bodies_other_info;

    private Boolean dismantling_or_restoration_works;

    @Column(nullable = true, length = 200)
    private String dismantling_dtls;

    private String dismantling_duration;

    @Column(nullable = true, length = 200)
    private String restoration_dtls;

    private String restoration_duration;

    @Column(nullable = true, length = 300)
    private String dismantling_or_restoration_other_info;

    private boolean cut_and_fill_excavations;

    @Column(nullable = true, length = 50)
    private Double cutting_quantity;


    @Column(nullable = true, length = 300)
    private String filling_quantity;

    @Column(nullable = true, length = 50)
    private Double cutting_material_dispose;

    @Column(nullable = true, length = 200)
    private String filling_material_source;

    @Column(nullable = true, length = 300)
    private String cut_and_fill_other_info;

    private Boolean underground_works;

    @Column(nullable = true, length = 50)
    private Double tunnel_length;

    @Column(nullable = true, length = 50)
    private Double muck_quantity;

    @Column(nullable = true, length = 200)
    private String muck_disposal_mode;

    @Column(nullable = true, length = 50)
    private Double reclamation_work_area;

    private String reclamation_mode;

    @Column(nullable = true, length = 300)
    private String underground_works_other_info;

    private boolean dredging_involved;

    @Column(nullable = true, length = 50)
    private Double dredging_area;

    @Column(nullable = true, length = 50)
    private Double dredging_material_quantity;

    @Column(nullable = true, length = 200)
    private String dredging_material_disposal_place;

    @Column(nullable = true, length = 300)
    private String dredging_other_info;

    private boolean offshore_structures_involved;

    @Column(nullable = true, length = 50)
    private Integer no_of_offshore_structures;

    @Column(nullable = true, length = 50)
    private Double built_up_area;

    @Column(nullable = true, length = 300)
    private String offshore_other_info;

    private boolean new_infrastructure;

    @Column(nullable = true, length = 50)
    private Double new_road_length;

    @Column(nullable = true, length = 50)
    private Double new_rail_length;

    @Column(nullable = true, length = 50)
    private Double new_jetty_length;

    @Column(nullable = true, length = 200)
    private String airport_details;

    @Column(nullable = true, length = 300)
    private String othr_trsprt_fclty;

    private boolean new_linear_structure;

    @Column(nullable = true, length = 50)
    private Double diverted_transmission_line_length;

    @Column(nullable = true, length = 50)
    private String diverted_transmission_line_length_unit;

    @Column(nullable = true, length = 50)
    private Double diverted_pipeline_length;

    @Column(nullable = true, length = 50)
    private String diverted_pipeline_length_unit;

    @Column(nullable = true, length = 50)
    private Double proposed_transmission_line_length;

    @Column(nullable = true, length = 50)
    private String proposed_transmission_line_length_unit;

    @Column(nullable = true, length = 50)
    private Double proposed_pipeline_length;

    @Column(nullable = true, length = 50)
    private String proposed_pipeline_length_unit;

    @Column(nullable = true, length = 300)
    private String linear_other_info;

    private boolean goods_storage_facility;

    @Column(nullable = true, length = 50)
    private Double covered_area_proposed;

    @Column(nullable = true)
    private String covered_area_proposed_unit;

    @Column(nullable = true, length = 50)
    private Double open_area_proposed;

    @Column(nullable = true)
    private String open_area_proposed_unit;

    @Column(nullable = true, length = 300)
    private String goods_storage_othr_info;

    private boolean housing_of_workers;

    @Column(nullable = true, length = 50)
    private Integer no_of_housing_units;

    @Column(nullable = true, length = 50)
    private Double housing_built_up_area;

    @Column(nullable = true, length = 50)
    private Double distance_from_project;

    @Column(nullable = true, length = 200)
    private String transport_facilities;

    @Column(nullable = true, length = 300)
    private String housing_of_workers_other_info;

    private boolean changes_to_aquifers;

    @Column(nullable = true, length = 50)
    private Double impoundment_lenght;

    @Column(nullable = true, length = 50)
    private Double damming_length;

    @Column(nullable = true, length = 50)
    private Double culverting_length;

    @Column(nullable = true, length = 50)
    private Double realignment_length;

    @Column(nullable = true, length = 50)
    private Double diversion_length;

    @Column(nullable = true, length = 300)
    private String aquifers_other_info;

    private boolean influx_to_area;

    @Column(nullable = true, length = 50)
    private Integer no_of_permanent_influx;

    @Column(nullable = true, length = 50)
    private Integer no_of_temporary_influx;

    @Column(nullable = true, length = 300)
    private String other_info_influx;

    private boolean any_other_change;
    @Column(nullable = true, length = 300)
    private String any_other_info;

    private boolean stream_change;


    private String other_information;

    private boolean is_deleted;

    private Double forest_land_area;
    @Column(length = 300)
    private String forest_land_remarks;
    private Double agriculture_land_area;
    
    @Column(length = 300)
    private String agriculture_land_remarks;
    private Double grazing_land_area;
    
    @Column(length = 300)
    private String grazing_land_remarks;
    private Double barren_land_area;
    
    @Column(length = 300)
    private String barren_land_remarks;
    private Double waste_land_area;
    
    @Column(length = 300)
    private String waste_land_remarks;
    private Double surface_water_bodies_area;
    
    @Column(length = 300)
    private String surface_water_bodies_remarks;
    private Double marshy_land_area;
    
    @Column(length = 300)
    private String marshy_land_remarks;
    private Double mangroves_area;
    
    @Column(length = 300)
    private String mangroves_remarks;
    private Double settlements_area;
    
    @Column(length = 300)
    private String settlements_remarks;
    private Double infrastructure_area;
    
    @Column(length = 300)
    private String infrastructure_remarks;
    private Double plantation_area;
    
    @Column(length = 300)
    private String plantation_remarks;
    private Double industrial_use_area;
    
    @Column(length = 300)
    private String industrial_use_remarks;
    
    @Column(nullable = true)
    private Integer no_of_stream;

    public Double getForest_land_area() {
        return forest_land_area;
    }

    public void setForest_land_area(Double forest_land_area) {
        this.forest_land_area = forest_land_area;
    }

    public String getForest_land_remarks() {
        return forest_land_remarks;
    }

    public void setForest_land_remarks(String forest_land_remarks) {
        this.forest_land_remarks = forest_land_remarks;
    }

    public Double getAgriculture_land_area() {
        return agriculture_land_area;
    }

    public void setAgriculture_land_area(Double agriculture_land_area) {
        this.agriculture_land_area = agriculture_land_area;
    }

    public String getAgriculture_land_remarks() {
        return agriculture_land_remarks;
    }

    public void setAgriculture_land_remarks(String agriculture_land_remarks) {
        this.agriculture_land_remarks = agriculture_land_remarks;
    }

    public Double getGrazing_land_area() {
        return grazing_land_area;
    }

    public void setGrazing_land_area(Double grazing_land_area) {
        this.grazing_land_area = grazing_land_area;
    }

    public String getGrazing_land_remarks() {
        return grazing_land_remarks;
    }

    public void setGrazing_land_remarks(String grazing_land_remarks) {
        this.grazing_land_remarks = grazing_land_remarks;
    }

    public Double getBarren_land_area() {
        return barren_land_area;
    }

    public void setBarren_land_area(Double barren_land_area) {
        this.barren_land_area = barren_land_area;
    }

    public String getBarren_land_remarks() {
        return barren_land_remarks;
    }

    public void setBarren_land_remarks(String barren_land_remarks) {
        this.barren_land_remarks = barren_land_remarks;
    }

    public Double getWaste_land_area() {
        return waste_land_area;
    }

    public void setWaste_land_area(Double waste_land_area) {
        this.waste_land_area = waste_land_area;
    }

    public String getWaste_land_remarks() {
        return waste_land_remarks;
    }

    public void setWaste_land_remarks(String waste_land_remarks) {
        this.waste_land_remarks = waste_land_remarks;
    }

    public Double getSurface_water_bodies_area() {
        return surface_water_bodies_area;
    }

    public void setSurface_water_bodies_area(Double surface_water_bodies_area) {
        this.surface_water_bodies_area = surface_water_bodies_area;
    }

    public String getSurface_water_bodies_remarks() {
        return surface_water_bodies_remarks;
    }

    public void setSurface_water_bodies_remarks(String surface_water_bodies_remarks) {
        this.surface_water_bodies_remarks = surface_water_bodies_remarks;
    }

    public Double getMarshy_land_area() {
        return marshy_land_area;
    }

    public void setMarshy_land_area(Double marshy_land_area) {
        this.marshy_land_area = marshy_land_area;
    }

    public String getMarshy_land_remarks() {
        return marshy_land_remarks;
    }

    public void setMarshy_land_remarks(String marshy_land_remarks) {
        this.marshy_land_remarks = marshy_land_remarks;
    }

    public Double getMangroves_area() {
        return mangroves_area;
    }

    public void setMangroves_area(Double mangroves_area) {
        this.mangroves_area = mangroves_area;
    }

    public String getMangroves_remarks() {
        return mangroves_remarks;
    }

    public void setMangroves_remarks(String mangroves_remarks) {
        this.mangroves_remarks = mangroves_remarks;
    }

    public Double getSettlements_area() {
        return settlements_area;
    }

    public void setSettlements_area(Double settlements_area) {
        this.settlements_area = settlements_area;
    }

    public String getSettlements_remarks() {
        return settlements_remarks;
    }

    public void setSettlements_remarks(String settlements_remarks) {
        this.settlements_remarks = settlements_remarks;
    }

    public Double getInfrastructure_area() {
        return infrastructure_area;
    }

    public void setInfrastructure_area(Double infrastructure_area) {
        this.infrastructure_area = infrastructure_area;
    }

    public String getInfrastructure_remarks() {
        return infrastructure_remarks;
    }

    public void setInfrastructure_remarks(String infrastructure_remarks) {
        this.infrastructure_remarks = infrastructure_remarks;
    }

    public Double getPlantation_area() {
        return plantation_area;
    }

    public void setPlantation_area(Double plantation_area) {
        this.plantation_area = plantation_area;
    }

    public String getPlantation_remarks() {
        return plantation_remarks;
    }

    public void setPlantation_remarks(String plantation_remarks) {
        this.plantation_remarks = plantation_remarks;
    }

    public Double getIndustrial_use_area() {
        return industrial_use_area;
    }

    public void setIndustrial_use_area(Double industrial_use_area) {
        this.industrial_use_area = industrial_use_area;
    }

    public String getIndustrial_use_remarks() {
        return industrial_use_remarks;
    }

    public void setIndustrial_use_remarks(String industrial_use_remarks) {
        this.industrial_use_remarks = industrial_use_remarks;
    }

    public Double getDemolition_work_no_total() {
        return demolition_work_no_total;
    }

    public void setDemolition_work_no_total(Double demolition_work_no_total) {
        this.demolition_work_no_total = demolition_work_no_total;
    }

    public Double getDemolition_work_area_total() {
        return demolition_work_area_total;
    }

    public void setDemolition_work_area_total(Double demolition_work_area_total) {
        this.demolition_work_area_total = demolition_work_area_total;
    }

    public String getOther_info_influx() {
        return other_info_influx;
    }

    public void setOther_info_influx(String other_info_influx) {
        this.other_info_influx = other_info_influx;
    }

    public boolean isAny_other_change() {
        return any_other_change;
    }

    public void setAny_other_change(boolean any_other_change) {
        this.any_other_change = any_other_change;
    }

    public String getAny_other_info() {
        return any_other_info;
    }

    public void setAny_other_info(String any_other_info) {
        this.any_other_info = any_other_info;
    }

    public boolean isStream_change() {
        return stream_change;
    }

    public void setStream_change(boolean stream_change) {
        this.stream_change = stream_change;
    }

    public EcPhysicalChanges() {
        this.is_deleted = false;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public EcPartB getEcPartB() {
        return ecPartB;
    }


    public void setEcPartB(EcPartB ecPartB) {
        this.ecPartB = ecPartB;
    }


    public boolean isChange_in_land_use() {
        return change_in_land_use;
    }


    public void setChange_in_land_use(boolean change_in_land_use) {
        this.change_in_land_use = change_in_land_use;
    }


    public Double getTotal_current_land_use_area() {
        return total_current_land_use_area;
    }


    public void setTotal_current_land_use_area(Double total_current_land_use_area) {
        this.total_current_land_use_area = total_current_land_use_area;
    }


    public Double getGreen_belt_land_requirement() {
        return green_belt_land_requirement;
    }


    public void setGreen_belt_land_requirement(Double green_belt_land_requirement) {
        this.green_belt_land_requirement = green_belt_land_requirement;
    }


    public Double getTotal_proposed_land_use() {
        return total_proposed_land_use;
    }


    public void setTotal_proposed_land_use(Double total_proposed_land_use) {
        this.total_proposed_land_use = total_proposed_land_use;
    }


    public Double getGreen_belt_existing_land_req() {
        return green_belt_existing_land_req;
    }


    public void setGreen_belt_existing_land_req(Double green_belt_existing_land_req) {
        this.green_belt_existing_land_req = green_belt_existing_land_req;
    }


    public Double getGreen_belt_proposed_land_req() {
        return green_belt_proposed_land_req;
    }


    public void setGreen_belt_proposed_land_req(Double green_belt_proposed_land_req) {
        this.green_belt_proposed_land_req = green_belt_proposed_land_req;
    }


    public Double getGreen_belt_total_land_req() {
        return green_belt_total_land_req;
    }


    public void setGreen_belt_total_land_req(Double green_belt_total_land_req) {
        this.green_belt_total_land_req = green_belt_total_land_req;
    }


    public Double getTotal_existing_land_req() {
        return total_existing_land_req;
    }


    public void setTotal_existing_land_req(Double total_existing_land_req) {
        this.total_existing_land_req = total_existing_land_req;
    }


    public Double getTotal_proposed_land_req() {
        return total_proposed_land_req;
    }


    public void setTotal_proposed_land_req(Double total_proposed_land_req) {
        this.total_proposed_land_req = total_proposed_land_req;
    }


    public Double getToal_land_req() {
        return toal_land_req;
    }


    public void setToal_land_req(Double toal_land_req) {
        this.toal_land_req = toal_land_req;
    }


    public boolean isDemolition_work_involved() {
        return demolition_work_involved;
    }


    public void setDemolition_work_involved(boolean demolition_work_involved) {
        this.demolition_work_involved = demolition_work_involved;
    }


    public boolean isTemporary_use_construction_work() {
        return temporary_use_construction_work;
    }


    public void setTemporary_use_construction_work(boolean temporary_use_construction_work) {
        this.temporary_use_construction_work = temporary_use_construction_work;
    }


    public boolean isExisting_vegetation_clearance() {
        return existing_vegetation_clearance;
    }


    public void setExisting_vegetation_clearance(boolean existing_vegetation_clearance) {
        this.existing_vegetation_clearance = existing_vegetation_clearance;
    }


    public Integer getNo_of_trees() {
        return no_of_trees;
    }


    public void setNo_of_trees(Integer no_of_trees) {
        this.no_of_trees = no_of_trees;
    }


    public Double getPlantation_area_to_be_cleared() {
        return plantation_area_to_be_cleared;
    }


    public void setPlantation_area_to_be_cleared(Double plantation_area_to_be_cleared) {
        this.plantation_area_to_be_cleared = plantation_area_to_be_cleared;
    }


    public Integer getTrees_proposed_for_plantation() {
        return trees_proposed_for_plantation;
    }


    public void setTrees_proposed_for_plantation(Integer trees_proposed_for_plantation) {
        this.trees_proposed_for_plantation = trees_proposed_for_plantation;
    }


    public String getVegetation_other_information() {
        return vegetation_other_information;
    }


    public void setVegetation_other_information(String vegetation_other_information) {
        this.vegetation_other_information = vegetation_other_information;
    }


    public Boolean getLoss_of_native_spieces() {
        return loss_of_native_spieces;
    }


    public void setLoss_of_native_spieces(Boolean loss_of_native_spieces) {
        this.loss_of_native_spieces = loss_of_native_spieces;
    }


    public String getLoss_of_native_spieces_dtls() {
        return loss_of_native_spieces_dtls;
    }


    public void setLoss_of_native_spieces_dtls(String loss_of_native_spieces_dtls) {
        this.loss_of_native_spieces_dtls = loss_of_native_spieces_dtls;
    }


    public String getLoss_of_native_spieces_othr_info() {
        return loss_of_native_spieces_othr_info;
    }


    public void setLoss_of_native_spieces_othr_info(String loss_of_native_spieces_othr_info) {
        this.loss_of_native_spieces_othr_info = loss_of_native_spieces_othr_info;
    }


    public boolean isLinear_structure_proposed_for_diversion() {
        return linear_structure_proposed_for_diversion;
    }


    public void setLinear_structure_proposed_for_diversion(boolean linear_structure_proposed_for_diversion) {
        this.linear_structure_proposed_for_diversion = linear_structure_proposed_for_diversion;
    }


    public String getLinear_structure_proposed_dtls() {
        return linear_structure_proposed_dtls;
    }


    public void setLinear_structure_proposed_dtls(String linear_structure_proposed_dtls) {
        this.linear_structure_proposed_dtls = linear_structure_proposed_dtls;
    }


    public Double getLinear_structure_length() {
        return linear_structure_length;
    }


    public void setLinear_structure_length(Double linear_structure_length) {
        this.linear_structure_length = linear_structure_length;
    }


    public Double getArea_covered_linear_structure() {
        return area_covered_linear_structure;
    }


    public void setArea_covered_linear_structure(Double area_covered_linear_structure) {
        this.area_covered_linear_structure = area_covered_linear_structure;
    }


    public String getLinear_structure_other_information() {
        return linear_structure_other_information;
    }


    public void setLinear_structure_other_information(String linear_structure_other_information) {
        this.linear_structure_other_information = linear_structure_other_information;
    }


    public boolean isExisting_transportation_change() {
        return existing_transportation_change;
    }


    public void setExisting_transportation_change(boolean existing_transportation_change) {
        this.existing_transportation_change = existing_transportation_change;
    }


    public Double getExisting_transportation_length() {
        return existing_transportation_length;
    }


    public void setExisting_transportation_length(Double existing_transportation_length) {
        this.existing_transportation_length = existing_transportation_length;
    }


    public String getAlternative_arrangement_proposed() {
        return alternative_arrangement_proposed;
    }


    public void setAlternative_arrangement_proposed(String alternative_arrangement_proposed) {
        this.alternative_arrangement_proposed = alternative_arrangement_proposed;
    }


    public String getExisting_transportation_other_info() {
        return existing_transportation_other_info;
    }


    public void setExisting_transportation_other_info(String existing_transportation_other_info) {
        this.existing_transportation_other_info = existing_transportation_other_info;
    }


    public boolean isWater_bodies_change() {
        return water_bodies_change;
    }


    public void setWater_bodies_change(boolean water_bodies_change) {
        this.water_bodies_change = water_bodies_change;
    }


    public String getWater_body_dtls() {
        return water_body_dtls;
    }


    public void setWater_body_dtls(String water_body_dtls) {
        this.water_body_dtls = water_body_dtls;
    }


    public Double getWater_bodies_proposed_area() {
        return water_bodies_proposed_area;
    }


    public void setWater_bodies_proposed_area(Double water_bodies_proposed_area) {
        this.water_bodies_proposed_area = water_bodies_proposed_area;
    }


    public Integer getNo_of_water_courses() {
        return no_of_water_courses;
    }


    public void setNo_of_water_courses(Integer no_of_water_courses) {
        this.no_of_water_courses = no_of_water_courses;
    }


    public Double getProposed_water_course_length() {
        return proposed_water_course_length;
    }


    public void setProposed_water_course_length(Double proposed_water_course_length) {
        this.proposed_water_course_length = proposed_water_course_length;
    }


    public String getWater_bodies_other_info() {
        return water_bodies_other_info;
    }


    public void setWater_bodies_other_info(String water_bodies_other_info) {
        this.water_bodies_other_info = water_bodies_other_info;
    }


    public Boolean getDismantling_or_restoration_works() {
        return dismantling_or_restoration_works;
    }


    public void setDismantling_or_restoration_works(Boolean dismantling_or_restoration_works) {
        this.dismantling_or_restoration_works = dismantling_or_restoration_works;
    }


    public String getDismantling_dtls() {
        return dismantling_dtls;
    }


    public void setDismantling_dtls(String dismantling_dtls) {
        this.dismantling_dtls = dismantling_dtls;
    }


    public String getDismantling_duration() {
        return dismantling_duration;
    }


    public void setDismantling_duration(String dismantling_duration) {
        this.dismantling_duration = dismantling_duration;
    }


    public String getRestoration_dtls() {
        return restoration_dtls;
    }


    public void setRestoration_dtls(String restoration_dtls) {
        this.restoration_dtls = restoration_dtls;
    }


    public String getRestoration_duration() {
        return restoration_duration;
    }


    public void setRestoration_duration(String restoration_duration) {
        this.restoration_duration = restoration_duration;
    }


    public String getDismantling_or_restoration_other_info() {
        return dismantling_or_restoration_other_info;
    }


    public void setDismantling_or_restoration_other_info(String dismantling_or_restoration_other_info) {
        this.dismantling_or_restoration_other_info = dismantling_or_restoration_other_info;
    }


    public boolean isCut_and_fill_excavations() {
        return cut_and_fill_excavations;
    }


    public void setCut_and_fill_excavations(boolean cut_and_fill_excavations) {
        this.cut_and_fill_excavations = cut_and_fill_excavations;
    }


    public Double getCutting_quantity() {
        return cutting_quantity;
    }


    public void setCutting_quantity(Double cutting_quantity) {
        this.cutting_quantity = cutting_quantity;
    }


    public String getFilling_quantity() {
        return filling_quantity;
    }


    public void setFilling_quantity(String filling_quantity) {
        this.filling_quantity = filling_quantity;
    }


    public Double getCutting_material_dispose() {
        return cutting_material_dispose;
    }

    public void setCutting_material_dispose(Double cutting_material_dispose) {
        this.cutting_material_dispose = cutting_material_dispose;
    }


    public String getFilling_material_source() {
        return filling_material_source;
    }


    public void setFilling_material_source(String filling_material_source) {
        this.filling_material_source = filling_material_source;
    }


    public String getCut_and_fill_other_info() {
        return cut_and_fill_other_info;
    }


    public void setCut_and_fill_other_info(String cut_and_fill_other_info) {
        this.cut_and_fill_other_info = cut_and_fill_other_info;
    }


    public Boolean getUnderground_works() {
        return underground_works;
    }


    public void setUnderground_works(Boolean underground_works) {
        this.underground_works = underground_works;
    }


    public Double getTunnel_length() {
        return tunnel_length;
    }


    public void setTunnel_length(Double tunnel_length) {
        this.tunnel_length = tunnel_length;
    }


    public Double getMuck_quantity() {
        return muck_quantity;
    }


    public void setMuck_quantity(Double muck_quantity) {
        this.muck_quantity = muck_quantity;
    }


    public String getMuck_disposal_mode() {
        return muck_disposal_mode;
    }


    public void setMuck_disposal_mode(String muck_disposal_mode) {
        this.muck_disposal_mode = muck_disposal_mode;
    }


    public Double getReclamation_work_area() {
        return reclamation_work_area;
    }


    public void setReclamation_work_area(Double reclamation_work_area) {
        this.reclamation_work_area = reclamation_work_area;
    }


    public String getReclamation_mode() {
        return reclamation_mode;
    }


    public void setReclamation_mode(String reclamation_mode) {
        this.reclamation_mode = reclamation_mode;
    }


    public String getUnderground_works_other_info() {
        return underground_works_other_info;
    }


    public void setUnderground_works_other_info(String underground_works_other_info) {
        this.underground_works_other_info = underground_works_other_info;
    }


    public boolean isDredging_involved() {
        return dredging_involved;
    }


    public void setDredging_involved(boolean dredging_involved) {
        this.dredging_involved = dredging_involved;
    }


    public Double getDredging_area() {
        return dredging_area;
    }


    public void setDredging_area(Double dredging_area) {
        this.dredging_area = dredging_area;
    }


    public Double getDredging_material_quantity() {
        return dredging_material_quantity;
    }


    public void setDredging_material_quantity(Double dredging_material_quantity) {
        this.dredging_material_quantity = dredging_material_quantity;
    }


    public String getDredging_material_disposal_place() {
        return dredging_material_disposal_place;
    }


    public void setDredging_material_disposal_place(String dredging_material_disposal_place) {
        this.dredging_material_disposal_place = dredging_material_disposal_place;
    }


    public String getDredging_other_info() {
        return dredging_other_info;
    }


    public void setDredging_other_info(String dredging_other_info) {
        this.dredging_other_info = dredging_other_info;
    }


    public boolean isOffshore_structures_involved() {
        return offshore_structures_involved;
    }


    public void setOffshore_structures_involved(boolean offshore_structures_involved) {
        this.offshore_structures_involved = offshore_structures_involved;
    }


    public Integer getNo_of_offshore_structures() {
        return no_of_offshore_structures;
    }


    public void setNo_of_offshore_structures(Integer no_of_offshore_structures) {
        this.no_of_offshore_structures = no_of_offshore_structures;
    }


    public Double getBuilt_up_area() {
        return built_up_area;
    }


    public void setBuilt_up_area(Double built_up_area) {
        this.built_up_area = built_up_area;
    }


    public String getOffshore_other_info() {
        return offshore_other_info;
    }


    public void setOffshore_other_info(String offshore_other_info) {
        this.offshore_other_info = offshore_other_info;
    }


    public boolean isNew_infrastructure() {
        return new_infrastructure;
    }


    public void setNew_infrastructure(boolean new_infrastructure) {
        this.new_infrastructure = new_infrastructure;
    }


    public Double getNew_road_length() {
        return new_road_length;
    }


    public void setNew_road_length(Double new_road_length) {
        this.new_road_length = new_road_length;
    }


    public Double getNew_rail_length() {
        return new_rail_length;
    }


    public void setNew_rail_length(Double new_rail_length) {
        this.new_rail_length = new_rail_length;
    }


    public Double getNew_jetty_length() {
        return new_jetty_length;
    }


    public void setNew_jetty_length(Double new_jetty_length) {
        this.new_jetty_length = new_jetty_length;
    }


    public String getAirport_details() {
        return airport_details;
    }


    public void setAirport_details(String airport_details) {
        this.airport_details = airport_details;
    }


    public String getOthr_trsprt_fclty() {
        return othr_trsprt_fclty;
    }


    public void setOthr_trsprt_fclty(String othr_trsprt_fclty) {
        this.othr_trsprt_fclty = othr_trsprt_fclty;
    }


    public boolean isNew_linear_structure() {
        return new_linear_structure;
    }


    public void setNew_linear_structure(boolean new_linear_structure) {
        this.new_linear_structure = new_linear_structure;
    }


    public Double getDiverted_transmission_line_length() {
        return diverted_transmission_line_length;
    }


    public void setDiverted_transmission_line_length(Double diverted_transmission_line_length) {
        this.diverted_transmission_line_length = diverted_transmission_line_length;
    }


    public Double getDiverted_pipeline_length() {
        return diverted_pipeline_length;
    }


    public void setDiverted_pipeline_length(Double diverted_pipeline_length) {
        this.diverted_pipeline_length = diverted_pipeline_length;
    }


    public Double getProposed_transmission_line_length() {
        return proposed_transmission_line_length;
    }


    public void setProposed_transmission_line_length(Double proposed_transmission_line_length) {
        this.proposed_transmission_line_length = proposed_transmission_line_length;
    }


    public Double getProposed_pipeline_length() {
        return proposed_pipeline_length;
    }


    public void setProposed_pipeline_length(Double proposed_pipeline_length) {
        this.proposed_pipeline_length = proposed_pipeline_length;
    }


    public String getLinear_other_info() {
        return linear_other_info;
    }


    public void setLinear_other_info(String linear_other_info) {
        this.linear_other_info = linear_other_info;
    }


    public boolean isGoods_storage_facility() {
        return goods_storage_facility;
    }


    public void setGoods_storage_facility(boolean goods_storage_facility) {
        this.goods_storage_facility = goods_storage_facility;
    }


    public Double getCovered_area_proposed() {
        return covered_area_proposed;
    }


    public void setCovered_area_proposed(Double covered_area_proposed) {
        this.covered_area_proposed = covered_area_proposed;
    }


    public Double getOpen_area_proposed() {
        return open_area_proposed;
    }


    public void setOpen_area_proposed(Double open_area_proposed) {
        this.open_area_proposed = open_area_proposed;
    }


    public String getGoods_storage_othr_info() {
        return goods_storage_othr_info;
    }


    public void setGoods_storage_othr_info(String goods_storage_othr_info) {
        this.goods_storage_othr_info = goods_storage_othr_info;
    }


    public boolean isHousing_of_workers() {
        return housing_of_workers;
    }


    public void setHousing_of_workers(boolean housing_of_workers) {
        this.housing_of_workers = housing_of_workers;
    }


    public Integer getNo_of_housing_units() {
        return no_of_housing_units;
    }


    public void setNo_of_housing_units(Integer no_of_housing_units) {
        this.no_of_housing_units = no_of_housing_units;
    }


    public Double getHousing_built_up_area() {
        return housing_built_up_area;
    }


    public void setHousing_built_up_area(Double housing_built_up_area) {
        this.housing_built_up_area = housing_built_up_area;
    }


    public Double getDistance_from_project() {
        return distance_from_project;
    }


    public void setDistance_from_project(Double distance_from_project) {
        this.distance_from_project = distance_from_project;
    }


    public String getTransport_facilities() {
        return transport_facilities;
    }


    public void setTransport_facilities(String transport_facilities) {
        this.transport_facilities = transport_facilities;
    }


    public String getHousing_of_workers_other_info() {
        return housing_of_workers_other_info;
    }


    public void setHousing_of_workers_other_info(String housing_of_workers_other_info) {
        this.housing_of_workers_other_info = housing_of_workers_other_info;
    }


    public boolean isChanges_to_aquifers() {
        return changes_to_aquifers;
    }


    public void setChanges_to_aquifers(boolean changes_to_aquifers) {
        this.changes_to_aquifers = changes_to_aquifers;
    }


    public Double getImpoundment_lenght() {
        return impoundment_lenght;
    }


    public void setImpoundment_lenght(Double impoundment_lenght) {
        this.impoundment_lenght = impoundment_lenght;
    }


    public Double getDamming_length() {
        return damming_length;
    }


    public void setDamming_length(Double damming_length) {
        this.damming_length = damming_length;
    }


    public Double getCulverting_length() {
        return culverting_length;
    }


    public void setCulverting_length(Double culverting_length) {
        this.culverting_length = culverting_length;
    }


    public Double getRealignment_length() {
        return realignment_length;
    }


    public void setRealignment_length(Double realignment_length) {
        this.realignment_length = realignment_length;
    }


    public Double getDiversion_length() {
        return diversion_length;
    }


    public void setDiversion_length(Double diversion_length) {
        this.diversion_length = diversion_length;
    }


    public String getAquifers_other_info() {
        return aquifers_other_info;
    }


    public void setAquifers_other_info(String aquifers_other_info) {
        this.aquifers_other_info = aquifers_other_info;
    }


    public boolean isInflux_to_area() {
        return influx_to_area;
    }


    public void setInflux_to_area(boolean influx_to_area) {
        this.influx_to_area = influx_to_area;
    }


    public Integer getNo_of_permanent_influx() {
        return no_of_permanent_influx;
    }


    public void setNo_of_permanent_influx(Integer no_of_permanent_influx) {
        this.no_of_permanent_influx = no_of_permanent_influx;
    }


    public Integer getNo_of_temporary_influx() {
        return no_of_temporary_influx;
    }


    public void setNo_of_temporary_influx(Integer no_of_temporary_influx) {
        this.no_of_temporary_influx = no_of_temporary_influx;
    }


    public String getOther_information() {
        return other_information;
    }


    public void setOther_information(String other_information) {
        this.other_information = other_information;
    }


    public boolean isIs_deleted() {
        return is_deleted;
    }


    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

	public String getWater_bodies_proposed_area_unit() {
		return water_bodies_proposed_area_unit;
	}

	public void setWater_bodies_proposed_area_unit(String water_bodies_proposed_area_unit) {
		this.water_bodies_proposed_area_unit = water_bodies_proposed_area_unit;
	}

	public String getProposed_water_course_length_unit() {
		return proposed_water_course_length_unit;
	}

	public void setProposed_water_course_length_unit(String proposed_water_course_length_unit) {
		this.proposed_water_course_length_unit = proposed_water_course_length_unit;
	}

	public String getDiverted_transmission_line_length_unit() {
		return diverted_transmission_line_length_unit;
	}

	public void setDiverted_transmission_line_length_unit(String diverted_transmission_line_length_unit) {
		this.diverted_transmission_line_length_unit = diverted_transmission_line_length_unit;
	}

	public String getDiverted_pipeline_length_unit() {
		return diverted_pipeline_length_unit;
	}

	public void setDiverted_pipeline_length_unit(String diverted_pipeline_length_unit) {
		this.diverted_pipeline_length_unit = diverted_pipeline_length_unit;
	}

	public String getProposed_transmission_line_length_unit() {
		return proposed_transmission_line_length_unit;
	}

	public void setProposed_transmission_line_length_unit(String proposed_transmission_line_length_unit) {
		this.proposed_transmission_line_length_unit = proposed_transmission_line_length_unit;
	}

	public String getProposed_pipeline_length_unit() {
		return proposed_pipeline_length_unit;
	}

	public void setProposed_pipeline_length_unit(String proposed_pipeline_length_unit) {
		this.proposed_pipeline_length_unit = proposed_pipeline_length_unit;
	}

	public String getCovered_area_proposed_unit() {
		return covered_area_proposed_unit;
	}

	public void setCovered_area_proposed_unit(String covered_area_proposed_unit) {
		this.covered_area_proposed_unit = covered_area_proposed_unit;
	}

	public String getOpen_area_proposed_unit() {
		return open_area_proposed_unit;
	}

	public void setOpen_area_proposed_unit(String open_area_proposed_unit) {
		this.open_area_proposed_unit = open_area_proposed_unit;
	}

	public Integer getNo_of_stream() {
		return no_of_stream;
	}

	public void setNo_of_stream(Integer no_of_stream) {
		this.no_of_stream = no_of_stream;
	}

    
}
