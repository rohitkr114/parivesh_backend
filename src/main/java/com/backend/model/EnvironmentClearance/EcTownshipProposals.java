package com.backend.model.EnvironmentClearance;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_township_proposal",schema = "master")
public class EcTownshipProposals extends Auditable<Integer> {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(nullable = true)
	private boolean whether_management_drainage;
	
	@Column(nullable = true, length = 300)
	private String whether_management_drainage_details;
	
	@Column(nullable = true, length = 500)
	private String whether_management_drainage_reason;
	
	@Column(nullable = true, length = 500)
	private String measure_ragarding_details;
	
	@Column(nullable = true, length = 500)
	private String land_use_impact;
	
	@Column(nullable = true)
	private boolean any_significant_land;
	
	@Column(nullable = true, length = 500)
	private String soil_type_details;
	
	@Column(nullable = true, length = 300)
	private String slope_anlysis_details;
	
	@Column(nullable = true, length = 300)
	private String vulnerability_subsidence_details;
	
	@Column(nullable = true, length = 300)
	private String seismicity_details;
	
	@Column(nullable = true, length = 300)
	private String others_details;
	
	@Column(nullable = true, length = 500)
	private String significant_land_reason;
	
	@Column(nullable = true)
	private boolean whether_soil_erosion;
	
	@Column(nullable = true, length = 300)
	private String soil_erosion_measures;
	
	@Column(nullable = true, length = 500)
	private String soil_erosion_reason;
	
	@Column(nullable = true, length = 300)
	private String traffic_management_details;
	
	@Column(nullable = true)
	private boolean whether_building_complexes;
	
	@Column(nullable = true, length = 300)
	private String energy_performance_index;
	
	@Column(nullable = true)
	private boolean whether_compliance_ecbc;
	
	@Column(nullable = true)
	private String whether_compliance_to;
	
	@Column(nullable = true, length = 300)
	private String whether_compliance_reason;
	
	@Column(nullable = true)
	private String fenestraion_u_factor;
	
	@Column(nullable = true, length = 500)
	private String fenestraion_u_factor_remarks;
	
	@Column(nullable = true)
	private String fenestraion_solar_heat_gain;
	
	@Column(nullable = true, length = 500)
	private String fenestraion_solar_heat_gain_remarks;
	
	@Column(nullable = true)
	private String fenestraion_visual_light;
	
	@Column(nullable = true, length = 500)
	private String fenestraion_visual_light_remarks;
	
	@Column(nullable = true, length = 50)
	private Double daylighting_udi_percentage;
	
	@Column(nullable = true, length = 500)
	private String daylighting_udi_percentage_remarks;
	
	@Column(nullable = true, length = 50)
	private Double daylighting_udi_area;
	
	@Column(nullable = true, length = 500)
	private String daylighting_udi_area_remarks;
	
	@Column(nullable = true, length = 50)
	private Double daylighting_udi_total_area;
	
	@Column(nullable = true, length = 500)
	private String daylighting_udi_total_area_remarks;
	
	@Column(nullable = true, length = 50)
	private Double envelope_roof_ufactor;
	
	@Column(nullable = true, length = 500)
	private String envelope_roof_ufactor_remarks;
	
	@Column(nullable = true, length = 100)
	private String envelope_climate_zone;
	
	@Column(nullable = true, length = 500)
	private String envelope_climate_zone_remarks;
	
	@Column(nullable = true, length = 50)
	private Double external_wall_opaque;
	
	@Column(nullable = true, length = 500)
	private String external_wall_opaque_remarks;
	
	@Column(nullable = true, length = 50)
	private Double external_wall_climate_zone;
	
	@Column(nullable = true, length = 500)
	private String external_wall_climate_zone_remarks;
	
	@Column(nullable = true, length = 50)
	private Double external_wall_materials;
	
	@Column(nullable = true, length = 500)
	private String external_wall_materials_remarks;
	
	@Column(nullable = true, length = 50)
	private Double external_wall_rvalue;
	
	@Column(nullable = true, length = 500)
	private String external_wall_rvalue_remarks;
	
	@Column(nullable = true, length = 300)
	private String energy_efficiency_thermal;
	
	@Column(nullable = true, length = 300)
	private String energy_efficiency_lightning;
	
	@Column(nullable = true)
	private boolean does_layout_streets;
	
	@Column(nullable = true, length = 300)
	private String does_layout_streets_details;
	
	@Column(nullable = true, length = 300)
	private String non_conventional_extent_energy;
	
	@Column(nullable = true, length = 300)
	private String building_likely_effects;
	
	@Column(nullable = true, length = 300)
	private String safety_precaution_measures;
	
	@Column(nullable = true, length = 300)
	private String noc_available_details;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "major_project_requirement_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MajorProjectRequirement> majorProjectRequirements;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "water_requirement_breakup_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<WaterRequirementBreakup> waterRequirementBreakups;

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

	public boolean isWhether_management_drainage() {
		return whether_management_drainage;
	}

	public void setWhether_management_drainage(boolean whether_management_drainage) {
		this.whether_management_drainage = whether_management_drainage;
	}

	public String getWhether_management_drainage_details() {
		return whether_management_drainage_details;
	}

	public void setWhether_management_drainage_details(String whether_management_drainage_details) {
		this.whether_management_drainage_details = whether_management_drainage_details;
	}

	public String getWhether_management_drainage_reason() {
		return whether_management_drainage_reason;
	}

	public void setWhether_management_drainage_reason(String whether_management_drainage_reason) {
		this.whether_management_drainage_reason = whether_management_drainage_reason;
	}

	public String getMeasure_ragarding_details() {
		return measure_ragarding_details;
	}

	public void setMeasure_ragarding_details(String measure_ragarding_details) {
		this.measure_ragarding_details = measure_ragarding_details;
	}

	public String getLand_use_impact() {
		return land_use_impact;
	}

	public void setLand_use_impact(String land_use_impact) {
		this.land_use_impact = land_use_impact;
	}

	public boolean isAny_significant_land() {
		return any_significant_land;
	}

	public void setAny_significant_land(boolean any_significant_land) {
		this.any_significant_land = any_significant_land;
	}

	public String getSoil_type_details() {
		return soil_type_details;
	}

	public void setSoil_type_details(String soil_type_details) {
		this.soil_type_details = soil_type_details;
	}

	public String getSlope_anlysis_details() {
		return slope_anlysis_details;
	}

	public void setSlope_anlysis_details(String slope_anlysis_details) {
		this.slope_anlysis_details = slope_anlysis_details;
	}

	public String getVulnerability_subsidence_details() {
		return vulnerability_subsidence_details;
	}

	public void setVulnerability_subsidence_details(String vulnerability_subsidence_details) {
		this.vulnerability_subsidence_details = vulnerability_subsidence_details;
	}

	public String getSeismicity_details() {
		return seismicity_details;
	}

	public void setSeismicity_details(String seismicity_details) {
		this.seismicity_details = seismicity_details;
	}

	public String getOthers_details() {
		return others_details;
	}

	public void setOthers_details(String others_details) {
		this.others_details = others_details;
	}

	public String getSignificant_land_reason() {
		return significant_land_reason;
	}

	public void setSignificant_land_reason(String significant_land_reason) {
		this.significant_land_reason = significant_land_reason;
	}

	public boolean isWhether_soil_erosion() {
		return whether_soil_erosion;
	}

	public void setWhether_soil_erosion(boolean whether_soil_erosion) {
		this.whether_soil_erosion = whether_soil_erosion;
	}

	public String getSoil_erosion_measures() {
		return soil_erosion_measures;
	}

	public void setSoil_erosion_measures(String soil_erosion_measures) {
		this.soil_erosion_measures = soil_erosion_measures;
	}

	public String getSoil_erosion_reason() {
		return soil_erosion_reason;
	}

	public void setSoil_erosion_reason(String soil_erosion_reason) {
		this.soil_erosion_reason = soil_erosion_reason;
	}

	public String getTraffic_management_details() {
		return traffic_management_details;
	}

	public void setTraffic_management_details(String traffic_management_details) {
		this.traffic_management_details = traffic_management_details;
	}

	public boolean isWhether_building_complexes() {
		return whether_building_complexes;
	}

	public void setWhether_building_complexes(boolean whether_building_complexes) {
		this.whether_building_complexes = whether_building_complexes;
	}

	public String getEnergy_performance_index() {
		return energy_performance_index;
	}

	public void setEnergy_performance_index(String energy_performance_index) {
		this.energy_performance_index = energy_performance_index;
	}

	public boolean isWhether_compliance_ecbc() {
		return whether_compliance_ecbc;
	}

	public void setWhether_compliance_ecbc(boolean whether_compliance_ecbc) {
		this.whether_compliance_ecbc = whether_compliance_ecbc;
	}

	public String getWhether_compliance_to() {
		return whether_compliance_to;
	}

	public void setWhether_compliance_to(String whether_compliance_to) {
		this.whether_compliance_to = whether_compliance_to;
	}

	public String getWhether_compliance_reason() {
		return whether_compliance_reason;
	}

	public void setWhether_compliance_reason(String whether_compliance_reason) {
		this.whether_compliance_reason = whether_compliance_reason;
	}

	public String getFenestraion_u_factor() {
		return fenestraion_u_factor;
	}

	public void setFenestraion_u_factor(String fenestraion_u_factor) {
		this.fenestraion_u_factor = fenestraion_u_factor;
	}

	public String getFenestraion_u_factor_remarks() {
		return fenestraion_u_factor_remarks;
	}

	public void setFenestraion_u_factor_remarks(String fenestraion_u_factor_remarks) {
		this.fenestraion_u_factor_remarks = fenestraion_u_factor_remarks;
	}

	public String getFenestraion_solar_heat_gain() {
		return fenestraion_solar_heat_gain;
	}

	public void setFenestraion_solar_heat_gain(String fenestraion_solar_heat_gain) {
		this.fenestraion_solar_heat_gain = fenestraion_solar_heat_gain;
	}

	public String getFenestraion_solar_heat_gain_remarks() {
		return fenestraion_solar_heat_gain_remarks;
	}

	public void setFenestraion_solar_heat_gain_remarks(String fenestraion_solar_heat_gain_remarks) {
		this.fenestraion_solar_heat_gain_remarks = fenestraion_solar_heat_gain_remarks;
	}

	public String getFenestraion_visual_light() {
		return fenestraion_visual_light;
	}

	public void setFenestraion_visual_light(String fenestraion_visual_light) {
		this.fenestraion_visual_light = fenestraion_visual_light;
	}

	public String getFenestraion_visual_light_remarks() {
		return fenestraion_visual_light_remarks;
	}

	public void setFenestraion_visual_light_remarks(String fenestraion_visual_light_remarks) {
		this.fenestraion_visual_light_remarks = fenestraion_visual_light_remarks;
	}

	public Double getDaylighting_udi_percentage() {
		return daylighting_udi_percentage;
	}

	public void setDaylighting_udi_percentage(Double daylighting_udi_percentage) {
		this.daylighting_udi_percentage = daylighting_udi_percentage;
	}

	public String getDaylighting_udi_percentage_remarks() {
		return daylighting_udi_percentage_remarks;
	}

	public void setDaylighting_udi_percentage_remarks(String daylighting_udi_percentage_remarks) {
		this.daylighting_udi_percentage_remarks = daylighting_udi_percentage_remarks;
	}

	public Double getDaylighting_udi_area() {
		return daylighting_udi_area;
	}

	public void setDaylighting_udi_area(Double daylighting_udi_area) {
		this.daylighting_udi_area = daylighting_udi_area;
	}

	public String getDaylighting_udi_area_remarks() {
		return daylighting_udi_area_remarks;
	}

	public void setDaylighting_udi_area_remarks(String daylighting_udi_area_remarks) {
		this.daylighting_udi_area_remarks = daylighting_udi_area_remarks;
	}

	public Double getDaylighting_udi_total_area() {
		return daylighting_udi_total_area;
	}

	public void setDaylighting_udi_total_area(Double daylighting_udi_total_area) {
		this.daylighting_udi_total_area = daylighting_udi_total_area;
	}

	public String getDaylighting_udi_total_area_remarks() {
		return daylighting_udi_total_area_remarks;
	}

	public void setDaylighting_udi_total_area_remarks(String daylighting_udi_total_area_remarks) {
		this.daylighting_udi_total_area_remarks = daylighting_udi_total_area_remarks;
	}

	public Double getEnvelope_roof_ufactor() {
		return envelope_roof_ufactor;
	}

	public void setEnvelope_roof_ufactor(Double envelope_roof_ufactor) {
		this.envelope_roof_ufactor = envelope_roof_ufactor;
	}

	public String getEnvelope_roof_ufactor_remarks() {
		return envelope_roof_ufactor_remarks;
	}

	public void setEnvelope_roof_ufactor_remarks(String envelope_roof_ufactor_remarks) {
		this.envelope_roof_ufactor_remarks = envelope_roof_ufactor_remarks;
	}

	public String getEnvelope_climate_zone_remarks() {
		return envelope_climate_zone_remarks;
	}

	public void setEnvelope_climate_zone_remarks(String envelope_climate_zone_remarks) {
		this.envelope_climate_zone_remarks = envelope_climate_zone_remarks;
	}

	public Double getExternal_wall_opaque() {
		return external_wall_opaque;
	}

	public void setExternal_wall_opaque(Double external_wall_opaque) {
		this.external_wall_opaque = external_wall_opaque;
	}

	public String getExternal_wall_opaque_remarks() {
		return external_wall_opaque_remarks;
	}

	public void setExternal_wall_opaque_remarks(String external_wall_opaque_remarks) {
		this.external_wall_opaque_remarks = external_wall_opaque_remarks;
	}

	public Double getExternal_wall_climate_zone() {
		return external_wall_climate_zone;
	}

	public void setExternal_wall_climate_zone(Double external_wall_climate_zone) {
		this.external_wall_climate_zone = external_wall_climate_zone;
	}

	public String getExternal_wall_climate_zone_remarks() {
		return external_wall_climate_zone_remarks;
	}

	public void setExternal_wall_climate_zone_remarks(String external_wall_climate_zone_remarks) {
		this.external_wall_climate_zone_remarks = external_wall_climate_zone_remarks;
	}

	public Double getExternal_wall_materials() {
		return external_wall_materials;
	}

	public void setExternal_wall_materials(Double external_wall_materials) {
		this.external_wall_materials = external_wall_materials;
	}

	public String getExternal_wall_materials_remarks() {
		return external_wall_materials_remarks;
	}

	public void setExternal_wall_materials_remarks(String external_wall_materials_remarks) {
		this.external_wall_materials_remarks = external_wall_materials_remarks;
	}

	public Double getExternal_wall_rvalue() {
		return external_wall_rvalue;
	}

	public void setExternal_wall_rvalue(Double external_wall_rvalue) {
		this.external_wall_rvalue = external_wall_rvalue;
	}

	public String getExternal_wall_rvalue_remarks() {
		return external_wall_rvalue_remarks;
	}

	public void setExternal_wall_rvalue_remarks(String external_wall_rvalue_remarks) {
		this.external_wall_rvalue_remarks = external_wall_rvalue_remarks;
	}

	public String getEnergy_efficiency_thermal() {
		return energy_efficiency_thermal;
	}

	public void setEnergy_efficiency_thermal(String energy_efficiency_thermal) {
		this.energy_efficiency_thermal = energy_efficiency_thermal;
	}

	public String getEnergy_efficiency_lightning() {
		return energy_efficiency_lightning;
	}

	public void setEnergy_efficiency_lightning(String energy_efficiency_lightning) {
		this.energy_efficiency_lightning = energy_efficiency_lightning;
	}

	public boolean isDoes_layout_streets() {
		return does_layout_streets;
	}

	public void setDoes_layout_streets(boolean does_layout_streets) {
		this.does_layout_streets = does_layout_streets;
	}

	public String getDoes_layout_streets_details() {
		return does_layout_streets_details;
	}

	public void setDoes_layout_streets_details(String does_layout_streets_details) {
		this.does_layout_streets_details = does_layout_streets_details;
	}

	public String getNon_conventional_extent_energy() {
		return non_conventional_extent_energy;
	}

	public void setNon_conventional_extent_energy(String non_conventional_extent_energy) {
		this.non_conventional_extent_energy = non_conventional_extent_energy;
	}

	public String getBuilding_likely_effects() {
		return building_likely_effects;
	}

	public void setBuilding_likely_effects(String building_likely_effects) {
		this.building_likely_effects = building_likely_effects;
	}

	public String getSafety_precaution_measures() {
		return safety_precaution_measures;
	}

	public void setSafety_precaution_measures(String safety_precaution_measures) {
		this.safety_precaution_measures = safety_precaution_measures;
	}

	public String getNoc_available_details() {
		return noc_available_details;
	}

	public void setNoc_available_details(String noc_available_details) {
		this.noc_available_details = noc_available_details;
	}

	public Set<MajorProjectRequirement> getMajorProjectRequirements() {
		return majorProjectRequirements;
	}

	public void setMajorProjectRequirements(Set<MajorProjectRequirement> majorProjectRequirements) {
		this.majorProjectRequirements = majorProjectRequirements;
	}

	public Set<WaterRequirementBreakup> getWaterRequirementBreakups() {
		return waterRequirementBreakups;
	}

	public void setWaterRequirementBreakups(Set<WaterRequirementBreakup> waterRequirementBreakups) {
		this.waterRequirementBreakups = waterRequirementBreakups;
	}
	
}

