package com.backend.repository.postgres.EcFormVPart2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFormVPart2Model.EcFormVPart2;

public interface EcFormVPart2Repository extends JpaRepository<EcFormVPart2, Integer> {

	@Query("select ec from EcFormVPart2 ec where ec.id=?1")
	public EcFormVPart2 getDetailsByEcId(Integer id);


	@Query(value = "select efvp.id from master.ec_form_v_part2 efvp, master.ec_form_v efv where efv.ec_id =?1 and efv.id = efvp.formv_id order by efv.id desc limit 1;",nativeQuery = true)
	Integer getFormVId(Integer torId);
}
