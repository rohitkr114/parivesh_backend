package com.backend.crz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.CrzDashboardDto;

public interface CrzDashboardDtoRepoitory extends JpaRepository<CrzDashboardDto, Integer> {


	@Query(value = " select cast(master.get_crz_application_list_my_task_fnc(:user_id) as varchar) ;", nativeQuery = true)
	String getCrzApplicationsListMyTask(Integer user_id);

	@Query(value = " select cast(master.get_crz_application_list_agenda_fnc(:user_id) as varchar) ;", nativeQuery = true)
	String getCrzApplicationsListAgenda(Integer user_id);

	@Query(value = " select cast(master.get_crz_application_list_porposal_history_fnc(:user_id) as varchar) ;", nativeQuery = true)
	String getApplicationsListPorposalHistory(Integer user_id);

	@Modifying
	@Query(value = "update process_engine.process_step_authority set status = :proponent_status where app_history_id =:id", nativeQuery = true)
	public void updateStatusForProposalApplication(@Param("proponent_status") String proponent_status, @Param("id") Integer id);

	@Modifying
    @Query(value = " Update process_engine.process_step_authority ps set status=:last_status where  ps.id in (Select ps1.id from process_engine.process_step_authority ps1 join process_engine.application_process_history aph on ps1.app_history_id =aph.id join master.proponent_applications pa on pa.proposal_no = aph.proposal_no where pa.proposal_id=:proposal_id)", nativeQuery = true)
    Integer updateLastStatus(@Param("proposal_id") Integer proposal_id, @Param("last_status") String last_status);
    
	
	@Query(value = " select caf_id from master.proponent_applications where id=?1 ", nativeQuery = true)
	public Integer getCafIdByProposalId(Integer id);

	@Modifying
	@Query(value = "update master.proponent_applications set last_status=:status where id=:proposal_id", nativeQuery = true)
	public void updateStatusForProponentApplicationsEDS(@Param("proposal_id") Integer proposal_id, @Param("status") String status);

	@Modifying
	@Query(value = "update master.caf_details set caf_status=:status where id=:caf_id", nativeQuery = true)
	public void updateStatusForCafDetailsEDS(@Param("caf_id") Integer caf_id, @Param("status") Integer status);

	@Query(value = " select cast(master.get_crz_application_list_raised_eds_fnc(:user_id) as varchar) ;", nativeQuery = true)
	String getCrzApplicationsListRaisedEDS(Integer user_id);
	
	@Query(value = " select cast(master.get_crz_application_list_forwarded_fnc(:user_id) as varchar) ;", nativeQuery = true)
	String getCrzApplicationsListForwarded(Integer user_id);
	
	@Query(value = " select cast(master.get_crz_accept_proposal_list_fnc(:user_id) as varchar) ;", nativeQuery = true)
	String getCrzApplicationsListApproved(Integer user_id);

}
