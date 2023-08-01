package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcSoilQuality;

public interface EcSoilQualityRepository extends JpaRepository<EcSoilQuality, Integer> {

	@Modifying
	@Query("update EcSoilQuality set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateSoilQuality(Integer id);

	@Query("select new EcSoilQuality(ecb.id, ecb.sq_location_lat, ecb.sq_location_long, ecb.sq_location_core,"
			+ "	ecb.sq_soil_texture, ecb.sq_particle_size_sand, ecb.sq_particle_size_silt,"
			+ "	ecb.sq_particle_size_clay, ecb.sq_water_holding_capacity, ecb.sq_porosity, ecb.sq_particle_size_sand_to, ecb.sq_particle_size_silt_to, ecb.sq_particle_size_clay_to,"
			+ "	ecb.sq_water_holding_capacity_to, ecb.sq_porosity_to) from EcSoilQuality ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcSoilQuality> getDataByEcId(Integer id);

	@Query(value = "select * from master.ec_soil_quality ecb where ecb.soil_quality_id=?1 and ecb.is_deleted='false'", nativeQuery = true)
	public Set<EcSoilQuality> getDataByFormId(Integer id);
}
