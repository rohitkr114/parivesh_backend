package com.backend.repository.postgres.EcForm11;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm11.EcForm11ProjectActivityDetails;

public interface EcForm11ProjectActivityDetailsRepository extends JpaRepository<EcForm11ProjectActivityDetails, Integer> {
	
	@Query(value="select efpad.* from master.ec_form_11_project_activity_details efpad where efpad.is_active =true and efpad.is_deleted =false and efpad.ec_form11_id =:ec_form11_id order by id",nativeQuery=true)
	public List<EcForm11ProjectActivityDetails> getProjectActivityDetails(@Param(value="ec_form11_id") Integer ec_form11_id);
}
