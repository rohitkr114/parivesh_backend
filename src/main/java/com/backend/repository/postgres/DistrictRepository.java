package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.District;
import com.backend.model.State;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{

	@Query("SELECT new District(d.id,d.code,d.state_code,d.name,d.census_code_2001,d.census_code_2011,d.is_active,d.is_deleted) from District d where d.is_active='true' and d.is_deleted='false' order by name")
    List<District> findAllDistricts();
	
	@Query("SELECT new District(d.id,d.code,d.state_code,d.name,d.census_code_2001,d.census_code_2011,d.is_active,d.is_deleted) from District d where d.is_active='true' and d.is_deleted='false' and d.state_code=?1 order by name")
    List<District> findAllDistrictsByStateCode(Integer id);
	
	@Query("SELECT new District(d.id,d.code,d.state_code,d.name,d.census_code_2001,d.census_code_2011,d.is_active,d.is_deleted) from District d where d.is_active='true' and d.is_deleted='false' and d.state_code=?1 and d.code=?2")
	District getDistrictByCode(Integer state_code,Integer district_code);
	
	@Query("SELECT new District(d.id,d.code,d.state_code,d.name,d.census_code_2001,d.census_code_2011,d.is_active,d.is_deleted) from District d where d.is_active='true' and d.is_deleted='false' and d.id=?1")
	District getDistrictById(Integer DistrictId);
	
	@Query("SELECT new District(d.id,d.code,d.state_code,d.name,d.census_code_2001,d.census_code_2011,d.is_active,d.is_deleted) from District d where d.is_active='true' and d.is_deleted='false' and d.code=?1")
	District findStateByDistrictCode(Integer DistrictCode);
	
}
