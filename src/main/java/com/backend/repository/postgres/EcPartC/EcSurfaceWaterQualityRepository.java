package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcSurfaceWaterQuality;

public interface EcSurfaceWaterQualityRepository extends JpaRepository<EcSurfaceWaterQuality, Integer> {

	@Modifying
	@Query("update EcSurfaceWaterQuality set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateSurfaceWaterQuality(Integer id);

	@Query("select new EcSurfaceWaterQuality(ecb.id, ecb.swq_location_lat, ecb.swq_location_long,"
			+ "	ecb.swq_location_core, ecb.swq_criterial_pollutant, ecb.swq_criterial_pollutant_other,"
			+ "	ecb.swq_unit, ecb.swq_unit_other, ecb.swq_observed_value, ecb.swq_observed_value_to, ecb.swq_is_2296,"
			+ "	ecb.swq_cpcb_criteria_class, ecb.swq_cpcb_criteria_standard) from EcSurfaceWaterQuality ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcSurfaceWaterQuality> getDataByEcId(Integer id);

	@Query(value ="select * from master.ec_surface_water_quality ecb where ecb.surface_water_quality_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcSurfaceWaterQuality> getDataByFormId(Integer id);
}
