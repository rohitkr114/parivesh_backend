package com.backend.repository.postgres.EcPartC;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcBaseLineDetails;

public interface EcBaseLineDetailsRepository extends JpaRepository<EcBaseLineDetails, Integer> {

	@Query("select new EcBaseLineDetails(ecb.id, ecb.temperature_min, ecb.temperature_max, ecb.temperature_mean,"
			+ "	ecb.wind_speed_min, ecb.wind_speed_max, ecb.wind_speed_mean, ecb.relative_humidity_min,"
			+ "	ecb.relative_humidity_max, ecb.relative_humidity_mean, ecb.solar_radiation_min,"
			+ "	ecb.solar_radiation_max, ecb.solar_radiation_mean, ecb.rainfall_total, ecb.rainfall_days,"
			+ "	ecb.rainfall_average, ecb.wind_direction, ecb.is_ground_water_intersection,"
			+ "	ecb.is_traffic_study_conducted, ecb.road_existing,"
			+ "	ecb.road_existing_v, ecb.road_existing_c, ecb.road_existing_ratio, ecb.road_existing_los,"
			+ "	ecb.road_proposed, ecb.road_proposed_v, ecb.road_proposed_c, ecb.road_proposed_ratio,"
			+ "	ecb.road_proposed_los, ecb.traffic_reason, ecb.is_any_species_found, ecb.species_details,"
			+ "	ecb.is_conservation_plan_species_prepared,"
			+ "	ecb.fund_provision_made, ecb.period_of_implementation, ecb.conservation_plan_reason,"
			+ "	ecb.is_conservation_plan_species_approved, ecb.letter_no,"
			+ "	ecb.issue_date, ecb.any_recommendation, ecb.conservation_plan_approved_reason, ecb.season, ecb.period_from, ecb.period_to,"
			+ "	ecb.meteorology, ecb.ambient_air, ecb.water_surface, ecb.ground_water,"
			+ "	ecb.ground_water_level, ecb.noise_level, ecb.soil_quality) from EcBaseLineDetails ecb where ecb.ecPartC.id=?1")
	public Optional<EcBaseLineDetails> getDataByEcId(Integer id);
	
	@Query("select new EcBaseLineDetails(ecb.id) from EcBaseLineDetails ecb where ecb.ecPartC.id=?1")
	public Optional<EcBaseLineDetails> getData(Integer id);

	@Query(value = "select ec.approval_copy_id,ec.conservation_plan_copy_id,ec.ground_water_authority_letter_id from master.ec_baseline_details ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);
}
