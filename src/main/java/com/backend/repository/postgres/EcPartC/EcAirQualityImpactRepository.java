package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcAirQualityImpacts;

public interface EcAirQualityImpactRepository extends JpaRepository<EcAirQualityImpacts, Integer> {

	@Modifying
	@Query("update EcAirQualityImpacts set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateAirQualityImpact(Integer id);
	
	@Query("select new EcAirQualityImpacts(ecb.id, ecb.aqi_location_lat, ecb.aqi_location_long, ecb.aqi_location_core,"
			+ "	ecb.aqi_criterial_pollutant, ecb.aqi_criterial_pollutant_other, ecb.aqi_unit,"
			+ "	ecb.aqi_baseline_concentration, ecb.aqi_predicted_incr_value, ecb.aqi_total_glc,"
			+ "	ecb.aqi_prescribed_standard) from EcAirQualityImpacts ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcAirQualityImpacts> getDataByEcId(Integer id);

	@Query(value = "select * from master.ec_air_quality_impacts ecb where ecb.air_quality_impact_id=?1 and ecb.is_deleted='false'", nativeQuery = true)
	public Set<EcAirQualityImpacts> getDataByFormId(Integer id);

}
