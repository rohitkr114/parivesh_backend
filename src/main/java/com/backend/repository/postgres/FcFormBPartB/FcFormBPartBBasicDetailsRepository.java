package com.backend.repository.postgres.FcFormBPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormBPartB.FcFormBPartBBasicDetails;

public interface FcFormBPartBBasicDetailsRepository extends JpaRepository<FcFormBPartBBasicDetails, Integer> {

	@Query("SELECT a from FcFormBPartBBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormBPartBBasicDetails findByIdActive(Integer id);

}
