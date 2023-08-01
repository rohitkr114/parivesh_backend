package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcGroundWaterQuality;

public interface EcGroundWaterQualityRepository extends JpaRepository<EcGroundWaterQuality, Integer> {

	@Modifying
	@Query("update EcGroundWaterQuality set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateGroundWaterQuality(Integer id);

	@Query("select new EcGroundWaterQuality(ecb.id, ecb.gwq_location_lat, ecb.gwq_location_long, ecb.gwq_location_core,"
			+ "	ecb.gwq_criterial_pollutant, ecb.gwq_criterial_pollutant_other, ecb.gwq_unit,"
			+ "	ecb.gwq_unit_other, ecb.gwq_observed_value, ecb.gwq_observed_value_to, ecb.gwq_desired_limits,"
			+ "	ecb.gwq_permissible_limits) from EcGroundWaterQuality ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcGroundWaterQuality> getDataByEcId(Integer id);

	@Query(value ="select * from master.ec_ground_water_quality ecb where ecb.ground_water_quality_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcGroundWaterQuality> getDataByFormId(Integer id);
}
