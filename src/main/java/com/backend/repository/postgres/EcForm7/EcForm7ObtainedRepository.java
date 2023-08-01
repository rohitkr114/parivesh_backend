package com.backend.repository.postgres.EcForm7;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm7.EcForm7Obtained;

public interface EcForm7ObtainedRepository extends JpaRepository<EcForm7Obtained, Integer>{

	@Modifying
	@Query("update EcForm7Obtained set is_delete='true' , is_active='false'  where id=?1")
	Integer delete(Integer id);
	
//	@Query("Select new EcForm7Obtained(em.id,em.ec_obtained_select,em.ec_obtained_date) from EcForm7Obtained em where em.ecForm7.id=?1 and em.is_delete='false'")
//	List<EcForm7Obtained> getByEcId(Integer id);
	
	@Query("Select em from EcForm7Obtained em where em.ecForm7ProjectActivity.id=?1 and em.is_delete='false'")
	List<EcForm7Obtained> getByEcId(Integer id);
	
	@Query(value = "select ec.ec_obtained_document_id from master.ec_form_7_obtained ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);
}
