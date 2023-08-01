package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcNoiseLevel;

public interface EcNoiseLevelRepository extends JpaRepository<EcNoiseLevel, Integer> {

	@Modifying
	@Query("update EcNoiseLevel set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateNoiseLevel(Integer id);

	@Query("select new EcNoiseLevel(ecb.id, ecb.nl_location_lat, ecb.nl_location_long, ecb.nl_location_core,"
			+ "	ecb.nl_category, ecb.nl_observed_noise_day_time, ecb.nl_observed_noise_night_time, ecb.nl_observed_noise_day_time_to, ecb.nl_observed_noise_night_time_to,"
			+ "	ecb.nl_prescribed_standard_day_time, ecb.nl_prescribed_standard_night_time) from EcNoiseLevel ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcNoiseLevel> getDataByEcId(Integer id);

	@Query(value = "select * from master.ec_noise_level ecb where ecb.noise_level_id=?1 and ecb.is_deleted='false'", nativeQuery = true)
	public Set<EcNoiseLevel> getDataByFormId(Integer id);
}
