package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.SubDistrict;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Integer>{

	@Query("SELECT s from SubDistrict s where s.district_code=?1 order by sub_district_name")
	List<SubDistrict> getSubDistrictsbyDistrictCode(Integer stateCode);
}
