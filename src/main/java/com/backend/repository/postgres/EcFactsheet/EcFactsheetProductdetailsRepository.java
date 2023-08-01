package com.backend.repository.postgres.EcFactsheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFactsheet.EcFactsheetProductdetails;

public interface EcFactsheetProductdetailsRepository extends JpaRepository<EcFactsheetProductdetails, Integer> {

//	@Modifying
//	@Query("Update EcFactsheetProductdetails set is_deleted='true' where id=?1")
//	public Integer updateEcFactsheetProductdetails(Integer id);

}
