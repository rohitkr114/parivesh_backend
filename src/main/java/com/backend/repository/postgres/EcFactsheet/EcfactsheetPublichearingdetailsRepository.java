package com.backend.repository.postgres.EcFactsheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFactsheet.EcfactsheetPublichearingdetails;

public interface EcfactsheetPublichearingdetailsRepository extends JpaRepository<EcfactsheetPublichearingdetails,Integer> {
	
//	@Modifying
//	@Query("Update EcfactsheetPublichearingdetails set is_deleted='true' where id=?1")
//	public Integer updateEcFactsheethearingdetails(Integer id);

}
