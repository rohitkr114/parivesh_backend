package com.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_product_details", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EcProductDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "is_envrmnt_sensitive_area_exist", nullable = true)
	private boolean is_envrmnt_sensitive_area_exist;

	@Column(name = "baseline_data_status", length = 100, nullable = true)
	private String baseline_data_status;

	@Column(name = "baseline_data_from", nullable = true)
	private Date baseline_data_from;

	@Column(name = "baseline_data_to", nullable = true)
	private Date baseline_data_to;
	
	
	@Column(name = "is_area_protected", nullable = true)
	private Boolean is_area_protected;
	
	@Column(name = "is_area_important", nullable = true)
	private Boolean is_area_important;
	
	@Column(name = "is_area_by_protected", nullable = true)
	private Boolean is_area_by_protected;
	
	@Column(name = "is_inland", nullable = true)
	private Boolean is_inland;
	
	@Column(name = "is_route", nullable = true)
	private Boolean is_route;
	
	@Column(name = "is_defence_installation", nullable = true)
	private Boolean is_defence_installation;
	
	@Column(name = "is_density_population", nullable = true)
	private Boolean is_density_population;
	
	@Column(name = "is_area_occupied", nullable = true)
	private Boolean is_area_occupied;
	
	@Column(name = "is_area_containing_important", nullable = true)
	private Boolean is_area_containing_important;
	
	@Column(name = "is_area_susceptible", nullable = true)
	private Boolean is_area_susceptible;

	@Column(name = "baseline_data_collection_season", length = 100, nullable = true)
	private String baseline_data_collection_season;

	@Column(name = "nml_meterology", nullable = true, length = 50)
	private Integer nml_meterology;
	
	@Column(name = "nml_ambient_air_quality", nullable = true, length = 5)
	private Integer nml_ambient_air_quality;
	
	@Column(name = "nml_ground_water_quality", nullable = true, length = 20)
	private Integer nml_ground_water_quality;
	
	@Column(name = "nml_surface_water_quality", nullable = true, length = 50)
	private Integer nml_surface_water_quality;
	
	@Column(name = "nml_phreatic_surface", nullable = true, length = 50)
	private Integer nml_phreatic_surface;
	
	@Column(name = "nml_noise_level", nullable = true, length = 50)
	private Integer nml_noise_level;

	/*
	 * @Column(name = "baseline_situation_summary", length = 500, nullable = true)
	 * private String baseline_situation_summary;
	 */
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "baseline_situation_summary_id", nullable = true)
	private DocumentDetails baseline_situation_summary;


	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "baseline_monitoing_location_map", nullable = true)
	private DocumentDetails baselineMonitoingLocationMap;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "baseline_data_collection_summary", nullable = true)
	private DocumentDetails baselineDataCollectionSummary;

	@Column(name = "is_eia_consultant_engaged", nullable = true)
	private boolean is_eia_consultant_engaged;

	@Column(name = "no_eia_consultant_engaged", length = 500, nullable = true)
	private String no_eia_consultant_engaged;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EnvironmentClearence enviromentClearence;
	
	@Column(nullable = true)
	private Integer nml_soil_quality;
	
	@Column(nullable = true, length = 500)
	private String accreditation_no_or_organisation_id;
	
	@Column(nullable = true, length = 500)
	private String consultant_name;
	
	@Column(nullable = true, length = 1000)
	private String consultant_address;
	
	@Column(nullable = true, length = 500)
	private String consultant_mobile;
	
	@Column(nullable = true, length = 500)
	private String consultant_email;
	
	@Column(nullable = true, length = 500)
	private String consultant_category;
	
	@Column(nullable = true, length = 1000)
	private String consultant_sectors;
	
	@Column(nullable = true, length = 500)
	private String validity_of_accreditation;

	public EcProductDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isIs_envrmnt_sensitive_area_exist() {
		return is_envrmnt_sensitive_area_exist;
	}

	public void setIs_envrmnt_sensitive_area_exist(boolean is_envrmnt_sensitive_area_exist) {
		this.is_envrmnt_sensitive_area_exist = is_envrmnt_sensitive_area_exist;
	}

	public String getBaseline_data_status() {
		return baseline_data_status;
	}

	public void setBaseline_data_status(String baseline_data_status) {
		this.baseline_data_status = baseline_data_status;
	}

	public Date getBaseline_data_from() {
		return baseline_data_from;
	}

	public void setBaseline_data_from(Date baseline_data_from) {
		this.baseline_data_from = baseline_data_from;
	}

	public Date getBaseline_data_to() {
		return baseline_data_to;
	}

	public void setBaseline_data_to(Date baseline_data_to) {
		this.baseline_data_to = baseline_data_to;
	}

	public String getBaseline_data_collection_season() {
		return baseline_data_collection_season;
	}

	public void setBaseline_data_collection_season(String baseline_data_collection_season) {
		this.baseline_data_collection_season = baseline_data_collection_season;
	}

	public DocumentDetails getBaselineMonitoingLocationMap() {
		return baselineMonitoingLocationMap;
	}

	public void setBaselineMonitoingLocationMap(DocumentDetails baselineMonitoingLocationMap) {
		this.baselineMonitoingLocationMap = baselineMonitoingLocationMap;
	}

	public DocumentDetails getBaselineDataCollectionSummary() {
		return baselineDataCollectionSummary;
	}

	public void setBaselineDataCollectionSummary(DocumentDetails baselineDataCollectionSummary) {
		this.baselineDataCollectionSummary = baselineDataCollectionSummary;
	}

	public boolean isIs_eia_consultant_engaged() {
		return is_eia_consultant_engaged;
	}

	public void setIs_eia_consultant_engaged(boolean is_eia_consultant_engaged) {
		this.is_eia_consultant_engaged = is_eia_consultant_engaged;
	}

	public String getNo_eia_consultant_engaged() {
		return no_eia_consultant_engaged;
	}

	public void setNo_eia_consultant_engaged(String no_eia_consultant_engaged) {
		this.no_eia_consultant_engaged = no_eia_consultant_engaged;
	}

	public EnvironmentClearence getEnviromentClearence() {
		return enviromentClearence;
	}

	public void setEnviromentClearence(EnvironmentClearence enviromentClearence) {
		this.enviromentClearence = enviromentClearence;
	}

	public Boolean getIs_area_protected() {
		return is_area_protected;
	}

	public void setIs_area_protected(Boolean is_area_protected) {
		this.is_area_protected = is_area_protected;
	}

	public Boolean getIs_area_important() {
		return is_area_important;
	}

	public void setIs_area_important(Boolean is_area_important) {
		this.is_area_important = is_area_important;
	}

	public Boolean getIs_area_by_protected() {
		return is_area_by_protected;
	}

	public void setIs_area_by_protected(Boolean is_area_by_protected) {
		this.is_area_by_protected = is_area_by_protected;
	}

	public Boolean getIs_inland() {
		return is_inland;
	}

	public void setIs_inland(Boolean is_inland) {
		this.is_inland = is_inland;
	}

	public Boolean getIs_route() {
		return is_route;
	}

	public void setIs_route(Boolean is_route) {
		this.is_route = is_route;
	}

	public Boolean getIs_defence_installation() {
		return is_defence_installation;
	}

	public void setIs_defence_installation(Boolean is_defence_installation) {
		this.is_defence_installation = is_defence_installation;
	}

	public Boolean getIs_density_population() {
		return is_density_population;
	}

	public void setIs_density_population(Boolean is_density_population) {
		this.is_density_population = is_density_population;
	}

	public Boolean getIs_area_occupied() {
		return is_area_occupied;
	}

	public void setIs_area_occupied(Boolean is_area_occupied) {
		this.is_area_occupied = is_area_occupied;
	}

	public Boolean getIs_area_containing_important() {
		return is_area_containing_important;
	}

	public void setIs_area_containing_important(Boolean is_area_containing_important) {
		this.is_area_containing_important = is_area_containing_important;
	}

	public Boolean getIs_area_susceptible() {
		return is_area_susceptible;
	}

	public void setIs_area_susceptible(Boolean is_area_susceptible) {
		this.is_area_susceptible = is_area_susceptible;
	}

	public Integer getNml_meterology() {
		return nml_meterology;
	}

	public void setNml_meterology(Integer nml_meterology) {
		this.nml_meterology = nml_meterology;
	}

	public Integer getNml_ambient_air_quality() {
		return nml_ambient_air_quality;
	}

	public void setNml_ambient_air_quality(Integer nml_ambient_air_quality) {
		this.nml_ambient_air_quality = nml_ambient_air_quality;
	}

	public Integer getNml_ground_water_quality() {
		return nml_ground_water_quality;
	}

	public void setNml_ground_water_quality(Integer nml_ground_water_quality) {
		this.nml_ground_water_quality = nml_ground_water_quality;
	}

	public Integer getNml_surface_water_quality() {
		return nml_surface_water_quality;
	}

	public void setNml_surface_water_quality(Integer nml_surface_water_quality) {
		this.nml_surface_water_quality = nml_surface_water_quality;
	}

	public Integer getNml_phreatic_surface() {
		return nml_phreatic_surface;
	}

	public void setNml_phreatic_surface(Integer nml_phreatic_surface) {
		this.nml_phreatic_surface = nml_phreatic_surface;
	}

	public Integer getNml_noise_level() {
		return nml_noise_level;
	}

	public void setNml_noise_level(Integer nml_noise_level) {
		this.nml_noise_level = nml_noise_level;
	}

	public DocumentDetails getBaseline_situation_summary() {
		return baseline_situation_summary;
	}

	public void setBaseline_situation_summary(DocumentDetails baseline_situation_summary) {
		this.baseline_situation_summary = baseline_situation_summary;
	}

	public Integer getNml_soil_quality() {
		return nml_soil_quality;
	}

	public void setNml_soil_quality(Integer nml_soil_quality) {
		this.nml_soil_quality = nml_soil_quality;
	}
  
	
}
