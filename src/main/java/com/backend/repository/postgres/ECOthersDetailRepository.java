package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcOthersDetail;


public interface ECOthersDetailRepository  extends JpaRepository<EcOthersDetail, Integer>  {

	@Query("select ec from EcOthersDetail ec where ec_id=?1")
	public Optional<EcOthersDetail> getRecordExist(Integer id);
	
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
	
}
