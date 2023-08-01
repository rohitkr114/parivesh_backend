package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcGroundWaterLevel;

public interface EcGroundWaterLevelRepository extends JpaRepository<EcGroundWaterLevel, Integer> {

	@Modifying
	@Query("update EcGroundWaterLevel set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateGroundWaterLevel(Integer id);

	@Query("select new EcGroundWaterLevel(ecb.id, ecb.gwl_location_lat, ecb.gwl_location_long, ecb.gwl_location_core,"
			+ "	ecb.gwl_pre_monsoon_from, ecb.gwl_pre_monsoon_to, ecb.gwl_post_monsoon_from,"
			+ "	ecb.gwl_post_monsoon_to) from EcGroundWaterLevel ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcGroundWaterLevel> getDataByEcId(Integer id);

	@Query(value ="select * from master.ec_ground_water_level ecb where ecb.ground_water_level_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcGroundWaterLevel> getDataByFormId(Integer id);
}
