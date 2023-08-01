package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcChallanDetails;
import com.backend.model.ClientCode.ClientCode;

public interface FcChallanDetailsRepository extends JpaRepository<FcChallanDetails, Integer>{

	@Query("SELECT a from FcChallanDetails a where a.is_active='true' and a.is_deleted='false' and a.applicationId=?1")
	FcChallanDetails findByApplicationIdActive(Integer applicationId);

	@Query(value="SELECT pa.proposal_sequence from master.proponent_applications pa where pa.id=?1",nativeQuery = true)
	Integer findProposalNumberByApplicationId(Integer applicationId);
	
	@Query(value="select * from master.client_code cc where cc.state_code=:state_code order by cc.id desc limit 1", nativeQuery=true)
	ClientCode getClientCodeByStateCode(@Param(value="state_code") Integer state_code);
	
	@Query(value="SELECT pa.moefcc_file_number from master.proponent_applications pa where pa.id=?1",nativeQuery = true)
	String findMoefccFileNoByApplicationId(Integer applicationId);
	
}
