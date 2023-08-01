package com.backend.repository.postgres.EcForm11;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm11.EcForm11SPCBDetails;

public interface EcForm11SPCBDetailsRepository extends JpaRepository<EcForm11SPCBDetails, Integer> {
	
	@Query(value="select efsd.* from master.ec_form_11_spcb_details efsd where efsd.is_active =true and efsd.is_deleted =false and efsd.ec_form_11_id=:ec_form_11_id order by id;",nativeQuery=true)
	public List<EcForm11SPCBDetails> getDataByForm11Id(@Param(value="ec_form_11_id") Integer ecForm11Id);
}
