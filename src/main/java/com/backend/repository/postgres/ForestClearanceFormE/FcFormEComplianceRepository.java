package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormECompliance;

public interface FcFormEComplianceRepository extends JpaRepository<FcFormECompliance, Integer> {

	@Query("Select pa from FcFormECompliance pa where pa.fcFormE.id=?1 and pa.is_delete='false'")
	List<FcFormECompliance> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormECompliance set is_delete='true' where id=?1")
	Integer deleteComplianceDetails(Integer fc_form_e_id);
}
