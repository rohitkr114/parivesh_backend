package com.backend.repository.postgres;

import com.backend.dto.FcEmailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.dto.FcEmailDto;
import com.backend.model.ForestClearance;

public interface ForestClearanceRepository extends JpaRepository<ForestClearance, Integer> {

	@Query("select c from ForestClearance c where c.id=?1")
	public ForestClearance findDetailByFcId(Integer id);

	@Query(value = " select * from master.fc_proposal_dtls_fnc(?1); ",nativeQuery = true)
    FcEmailDto getFcEmailData(String proposal_no);

	@Query(value = " select mm.id from master.mining_mineral_oil_proposal mm where mm.fc_id =?1 ", nativeQuery = true)
	public Integer getMiningMineralOilProposal(Integer fcId);
	
	@Query(value = "select ue.mobilenumber,ue.emailid from authentication.user_entity ue inner join authentication.\"role\" r on (ue.selected_role=r.entityid) inner join ua.office_entity oe on(oe.entityid=ue.selected_office) where ue.entityid=:userId limit 1", nativeQuery = true)
	public FcEmailDto getUserInfo(@Param("userId") Integer userId);

	@Modifying
	@Query(value = "Update master.forest_clearance set proposal_no =?2 where id =?1", nativeQuery = true)
	public Integer updateFcFormAProposal(Integer id, String proposal_no);

}
