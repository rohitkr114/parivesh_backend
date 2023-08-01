package com.backend.repository.postgres.EcFormVPart2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFormVPart2Model.EcFormVPart2PublicHearingdetails;

public interface EcFormVPart2PublicHearingdetailsRepository extends JpaRepository<EcFormVPart2PublicHearingdetails,Integer> {
	
//	@Modifying
//	@Query("Update EcFormVPart2PublicHearingdetails set is_deleted='true' where id=?1")
//	public Integer updateEcFormVPart2PublicHearingdetails(Integer id);
}
