package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcAmbientAirQuality;

public interface EcAmbientAirQualityRepository extends JpaRepository<EcAmbientAirQuality, Integer> {

	@Modifying
	@Query("update EcAmbientAirQuality set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateEcAmbientAirQuality(Integer id);

	@Query("select new EcAmbientAirQuality( ea.id,  ea.aaq_location_lat,  ea.aaq_location_long,  ea.aaq_location_core,"
			+ "	 ea.aaq_criterial_pollutant,  ea.aaq_criterial_pollutant_other,  ea.aaq_unit,"
			+ "	 ea.aaq_unit_other,  ea.aaq_max_value,  ea.aaq_min_value,  ea.aaq_percentile_value,"
			+ "	 ea.aaq_percentile_value_to, ea.aaq_prescribed_standard) from EcAmbientAirQuality ea where ea.ecPartC.id=?1 and ea.is_deleted='false'")
	public List<EcAmbientAirQuality> getDataByEcPartCId(Integer id);
	
	@Query(value ="select * from master.ec_ambient_air_quality ea where ea.ambient_air_quality_id=?1 and ea.is_deleted='false'",nativeQuery = true)
	public Set<EcAmbientAirQuality> getDataByFormId(Integer id);
}
