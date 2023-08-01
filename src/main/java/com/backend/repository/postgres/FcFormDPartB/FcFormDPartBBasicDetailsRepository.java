package com.backend.repository.postgres.FcFormDPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.backend.model.FcFormDPartB.FcFormDPartBBasicDetails;

public interface FcFormDPartBBasicDetailsRepository extends JpaRepository<FcFormDPartBBasicDetails, Integer> {
	
	@Query("SELECT a from FcFormDPartBBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormDPartBBasicDetails findByIdActive(Integer id);

}
