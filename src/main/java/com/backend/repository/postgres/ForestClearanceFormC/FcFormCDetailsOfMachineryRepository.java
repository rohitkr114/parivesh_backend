package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCDetailsOfMachinery;

@Transactional
public interface FcFormCDetailsOfMachineryRepository extends JpaRepository<FcFormCDetailsOfMachinery, Integer>{

	@Query("SELECT fc from FcFormCDetailsOfMachinery fc where fc.is_delete ='false' and fc.fcFormC.id=?1")
	List<FcFormCDetailsOfMachinery> findByFcFormCByID(Integer id);

	@Modifying
	@Query("update  FcFormCDetailsOfMachinery set is_delete='true' where id=?1")
	Integer updateFcFormCById(int id);
}
