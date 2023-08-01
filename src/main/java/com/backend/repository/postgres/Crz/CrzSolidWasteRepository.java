package com.backend.repository.postgres.Crz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.Crz.CrzSolidWaste;

public interface CrzSolidWasteRepository extends JpaRepository<CrzSolidWaste, Integer>{

	@Query("select new CrzSolidWaste(id,type_of_waste,quantity_of_solid_waste_generated,mode_of_disposal) from CrzSolidWaste where crz_basic_details_id=?1 and is_deleted = false ")
	List<CrzSolidWaste> getDataByCrzId(Integer id);
}
