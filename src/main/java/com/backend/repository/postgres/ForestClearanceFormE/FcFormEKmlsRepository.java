package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormEKmls;

public interface FcFormEKmlsRepository extends JpaRepository<FcFormEKmls, Integer> {

	@Query("select new FcFormEKmls(id) from FcFormEKmls where fc_form_e_id=?1")
	FcFormEKmls getByKmlId(Integer id);

	@Query("Select pa from FcFormEKmls pa where pa.fcFormE.id=?1 and pa.is_delete='false'")
	List<FcFormEKmls> getByFcID(Integer id);

	@Modifying
	@Query("update FcFormEKmls set is_delete='true' where id=?1")
	Integer deleteKmls(Integer fc_form_e_id);

}
