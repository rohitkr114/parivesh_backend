package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12Obtained;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EcForm12ObtainedRepository extends JpaRepository<EcForm12Obtained, Integer>{

	@Modifying
	@Query("update EcForm12Obtained set is_delete='true' , is_active='false'  where id=?1")
	Integer delete(Integer id);
	
//	@Query("Select new EcForm12Obtained(em.id,em.ec_obtained_select,em.ec_obtained_date) from EcForm12Obtained em where em.ecForm12.id=?1 and em.is_delete='false'")
//	List<EcForm12Obtained> getByEcId(Integer id);
	
	@Query("Select em from EcForm12Obtained em where em.ecForm12ProjectActivity.id=?1 and em.is_delete='false'")
	List<EcForm12Obtained> getByEcId(Integer id);
	
	@Query(value = "select ec.ec_obtained_document_id from master.ec_form_12_obtained ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);
}
