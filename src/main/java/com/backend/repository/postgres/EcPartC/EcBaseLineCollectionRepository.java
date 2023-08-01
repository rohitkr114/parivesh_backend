package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcBaseLineCollections;

public interface EcBaseLineCollectionRepository extends JpaRepository<EcBaseLineCollections, Integer> {

	@Modifying
	@Query("update EcBaseLineCollections set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateBaseLineCollection(Integer id);

	@Query("select new EcBaseLineCollections(ecb.id, ecb.season, ecb.period_from, ecb.period_to, ecb.meteorology,"
			+ "	ecb.ambient_air, ecb.water_surface, ecb.ground_water, ecb.ground_water_level,"
			+ "	ecb.noise_level, ecb.soil_quality) from EcBaseLineCollections ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcBaseLineCollections> getDataByEcId(Integer id);
	
	@Query(value ="select * from master.ec_baseline_collection ecb where ecb.baseline_collection_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcBaseLineCollections> getDataByFormId(Integer id);
}
