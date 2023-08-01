package com.backend.repository.postgres.Crz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Crz.CrzWaterRequirement;

public interface CrzWaterRequirementRepository extends JpaRepository<CrzWaterRequirement, Integer>{

	
	@Query("select new CrzWaterRequirement(id,source,quantity) from CrzWaterRequirement where crz_basic_details_id=?1 and is_deleted = false ")
	List<CrzWaterRequirement> getDataByCrzId(Integer id);
}
