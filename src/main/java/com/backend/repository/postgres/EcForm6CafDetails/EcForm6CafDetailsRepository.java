package com.backend.repository.postgres.EcForm6CafDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm6CafDetails.EcForm6CafDetails;

public interface EcForm6CafDetailsRepository extends JpaRepository<EcForm6CafDetails, Integer> {
	
	@Query(value = "select * from master.ec_form6_caf_details ec where ec.new_caf_id=?1", nativeQuery = true)
	public EcForm6CafDetails getEcForm6CafDetailsByNewCafId(Integer new_caf_id);

	
}
