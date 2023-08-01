package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Village;

public interface VillageRepository extends JpaRepository<Village, Integer>{

	@Query("SELECT v from Village v where v.sub_district_code=?1 order by district_name")
	List<Village> getVillagesbySubDistrictCode(Integer SubDistrictCode);
	
	@Query("SELECT v from Village v where v.district=?1 order by district_name")
	List<Village> getVillagesbyDistrictCode(Integer DistrictCode);
}
