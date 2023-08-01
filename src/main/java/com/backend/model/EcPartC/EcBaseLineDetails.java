package com.backend.model.EcPartC;

import java.util.Date;
import java.util.HashSet;
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
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ec_baseline_details", schema = "master")
public class EcBaseLineDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EcPartC ecPartC;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "baseline_collection_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcBaseLineCollections> ecBaseLineCollections = new HashSet<>();

	@Column(name = "temperature_min", nullable = true, length = 53)
	private Double temperature_min;

	@Column(name = "temperature_max", nullable = true, length = 53)
	private Double temperature_max;

	@Column(name = "temperature_mean", nullable = true, length = 53)
	private Double temperature_mean;

	@Column(name = "wind_speed_min", nullable = true, length = 53)
	private Double wind_speed_min;

	@Column(name = "wind_speed_max", nullable = true, length = 53)
	private Double wind_speed_max;

	@Column(name = "wind_speed_mean", nullable = true, length = 53)
	private Double wind_speed_mean;

	@Column(name = "relative_humidity_min", nullable = true, length = 53)
	private Double relative_humidity_min;

	@Column(name = "relative_humidity_max", nullable = true, length = 53)
	private Double relative_humidity_max;

	@Column(name = "relative_humidity_mean", nullable = true, length = 53)
	private Double relative_humidity_mean;

	@Column(name = "solar_radiation_min", nullable = true, length = 53)
	private Double solar_radiation_min;

	@Column(name = "solar_radiation_max", nullable = true, length = 53)
	private Double solar_radiation_max;

	@Column(name = "solar_radiation_mean", nullable = true, length = 53)
	private Double solar_radiation_mean;

	@Column(name = "rainfall_total", nullable = true, length = 53)
	private Double rainfall_total;

	@Column(name = "rainfall_days", nullable = true, length = 53)
	private Double rainfall_days;

	@Column(name = "rainfall_average", nullable = true, length = 53)
	private Double rainfall_average;

	@Column(name = "wind_direction", nullable = true)
	private String wind_direction;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ambient_air_quality_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcAmbientAirQuality> ecAmbientAirQualities = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "surface_water_quality_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcSurfaceWaterQuality> ecSurfaceWaterQualities = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ground_water_quality_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcGroundWaterQuality> ecGroundWaterQualities = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ground_water_level_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcGroundWaterLevel> ecGroundWaterLevels = new HashSet<>();

	@Column(name = "is_ground_water_intersection", nullable = true)
	private Boolean is_ground_water_intersection;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ground_water_authority_letter_id", nullable = true)
	private DocumentDetails ground_water_authority_letter;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "noise_level_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcNoiseLevel> ecNoiseLevels = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "soil_quality_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcSoilQuality> ecSoilQualities = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "chemical_property_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcChemicalProperties> ecChemicalProperties = new HashSet<>();

	@Column(name = "is_traffic_study_conducted", nullable = true)
	private Boolean is_traffic_study_conducted;

	@Column(name = "road_existing", nullable = true)
	private String road_existing;

	@Column(name = "road_existing_v", nullable = true, length = 53)
	private Double road_existing_v;

	@Column(name = "road_existing_c", nullable = true, length = 53)
	private Double road_existing_c;

	@Column(name = "road_existing_ratio", nullable = true, length = 53)
	private Double road_existing_ratio;

	@Column(name = "road_existing_los", nullable = true, length = 53)
	private Double road_existing_los;

	@Column(name = "road_proposed", nullable = true)
	private String road_proposed;

	@Column(name = "road_proposed_v", nullable = true, length = 53)
	private Double road_proposed_v;

	@Column(name = "road_proposed_c", nullable = true, length = 53)
	private Double road_proposed_c;

	@Column(name = "road_proposed_ratio", nullable = true, length = 53)
	private Double road_proposed_ratio;

	@Column(name = "road_proposed_los", nullable = true, length = 53)
	private Double road_proposed_los;

	@Column(name = "traffic_reason", nullable = true, length = 500)
	private String traffic_reason;

	@Column(name = "is_any_species_found", nullable = true)
	private Boolean is_any_species_found;

	@Column(name = "species_details", nullable = true)
	private String species_details;

	@Column(name = "is_conservation_plan_species_prepared", nullable = true)
	private Boolean is_conservation_plan_species_prepared;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "conservation_plan_copy_id", nullable = true)
	private DocumentDetails conservation_plan_copy;

	@Column(name = "fund_provision_made", nullable = true)
	private Double fund_provision_made;

	@Column(name = "period_of_implementation", nullable = true, length = 10)
	private String period_of_implementation;

	@Column(name = "conservation_plan_reason", nullable = true, length = 500)
	private String conservation_plan_reason;

	@Column(name = "is_conservation_plan_species_approved", nullable = true)
	private Boolean is_conservation_plan_species_approved;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "approval_copy_id", nullable = true)
	private DocumentDetails approval_copy;

	@Column(name = "letter_no", nullable = true, length = 20)
	private String letter_no;

	@Column(name = "issue_date", nullable = true)
	private Date issue_date;

	@Column(name = "any_recommendation", nullable = true, length = 300)
	private String any_recommendation;

	@Column(name = "conservation_plan_approved_reason", nullable = true, length = 500)
	private String conservation_plan_approved_reason;

	@Column(name = "season", nullable = true)
	private String season;

	@Column(name = "period_from", nullable = true)
	private Date period_from;

	@Column(name = "period_to", nullable = true)
	private Date period_to;

	@Column(name = "meteorology", nullable = true, length = 53)
	private String meteorology;

	@Column(name = "ambient_air", nullable = true, length = 53)
	private String ambient_air;

	@Column(name = "water_surface", nullable = true, length = 53)
	private String water_surface;

	@Column(name = "ground_water", nullable = true, length = 53)
	private String ground_water;

	@Column(name = "ground_water_level", nullable = true, length = 53)
	private String ground_water_level;

	@Column(name = "noise_level", nullable = true, length = 53)
	private String noise_level;

	@Column(name = "soil_level", nullable = true, length = 53)
	private String soil_quality;
	
	public EcBaseLineDetails(Integer id) {
		this.id = id;
	}

	public EcBaseLineDetails(Integer id, Double temperature_min, Double temperature_max, Double temperature_mean,
			Double wind_speed_min, Double wind_speed_max, Double wind_speed_mean, Double relative_humidity_min,
			Double relative_humidity_max, Double relative_humidity_mean, Double solar_radiation_min,
			Double solar_radiation_max, Double solar_radiation_mean, Double rainfall_total, Double rainfall_days,
			Double rainfall_average, String wind_direction, Boolean is_ground_water_intersection,
			Boolean is_traffic_study_conducted, String road_existing, Double road_existing_v, Double road_existing_c,
			Double road_existing_ratio, Double road_existing_los, String road_proposed, Double road_proposed_v,
			Double road_proposed_c, Double road_proposed_ratio, Double road_proposed_los, String traffic_reason,
			Boolean is_any_species_found, String species_details, Boolean is_conservation_plan_species_prepared,
			Double fund_provision_made, String period_of_implementation, String conservation_plan_reason,
			Boolean is_conservation_plan_species_approved, String letter_no, Date issue_date, String any_recommendation,
			String conservation_plan_approved_reason, String season, Date period_from, Date period_to,
			String meteorology, String ambient_air, String water_surface, String ground_water,
			String ground_water_level, String noise_level, String soil_quality) {
		this.id = id;
		this.temperature_min = temperature_min;
		this.temperature_max = temperature_max;
		this.temperature_mean = temperature_mean;
		this.wind_speed_min = wind_speed_min;
		this.wind_speed_max = wind_speed_max;
		this.wind_speed_mean = wind_speed_mean;
		this.relative_humidity_min = relative_humidity_min;
		this.relative_humidity_max = relative_humidity_max;
		this.relative_humidity_mean = relative_humidity_mean;
		this.solar_radiation_min = solar_radiation_min;
		this.solar_radiation_max = solar_radiation_max;
		this.solar_radiation_mean = solar_radiation_mean;
		this.rainfall_total = rainfall_total;
		this.rainfall_days = rainfall_days;
		this.rainfall_average = rainfall_average;
		this.wind_direction = wind_direction;
		this.is_ground_water_intersection = is_ground_water_intersection;
		this.is_traffic_study_conducted = is_traffic_study_conducted;
		this.road_existing = road_existing;
		this.road_existing_v = road_existing_v;
		this.road_existing_c = road_existing_c;
		this.road_existing_ratio = road_existing_ratio;
		this.road_existing_los = road_existing_los;
		this.road_proposed = road_proposed;
		this.road_proposed_v = road_proposed_v;
		this.road_proposed_c = road_proposed_c;
		this.road_proposed_ratio = road_proposed_ratio;
		this.road_proposed_los = road_proposed_los;
		this.traffic_reason = traffic_reason;
		this.is_any_species_found = is_any_species_found;
		this.species_details = species_details;
		this.is_conservation_plan_species_prepared = is_conservation_plan_species_prepared;
		this.fund_provision_made = fund_provision_made;
		this.period_of_implementation = period_of_implementation;
		this.conservation_plan_reason = conservation_plan_reason;
		this.is_conservation_plan_species_approved = is_conservation_plan_species_approved;
		this.letter_no = letter_no;
		this.issue_date = issue_date;
		this.any_recommendation = any_recommendation;
		this.conservation_plan_approved_reason = conservation_plan_approved_reason;
		this.season = season;
		this.period_from = period_from;
		this.period_to = period_to;
		this.meteorology = meteorology;
		this.ambient_air = ambient_air;
		this.water_surface = water_surface;
		this.ground_water = ground_water;
		this.ground_water_level = ground_water_level;
		this.noise_level = noise_level;
		this.soil_quality = soil_quality;
	}
}
