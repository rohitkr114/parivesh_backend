package com.backend.repository.postgres.EcFactsheet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFactsheet.EcFactsheetOtherDetails;

public interface EcFactsheetOtherDetailsRepository extends JpaRepository<EcFactsheetOtherDetails,Integer> {
	
	@Query("select ecf from EcFactsheetOtherDetails ecf where ecf.ecFactsheet.id=?1 ")
	public EcFactsheetOtherDetails getDatabyFactsheetId(Integer ec_factsheet_id); 

}
