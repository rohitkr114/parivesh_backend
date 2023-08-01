package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAgendaProposalsDto;

@Repository
public interface CrzAgendaProposalsRepository extends JpaRepository<CrzAgendaProposalsDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_agenda_proposals u WHERE u.agenda_id =?1 order by 1 asc", nativeQuery = true)
	List<CrzAgendaProposalsDto> findByAgenda_id(Integer id);
	
	@Modifying
	@Query(value = "update master.crz_agenda_proposals set mom_previous_delibration = :mom_previous_delibration, mom_current_delibration = :mom_current_delibration, mom_recommendation = :mom_recommendation, chairman_remarks = :chairman_remarks, salient_feature = :salient_feature  where id =:id", nativeQuery = true)
	public void updateAgendaProposalForMom(@Param("mom_previous_delibration") String mom_previous_delibration, @Param("mom_current_delibration") String mom_current_delibration, @Param("mom_recommendation") String mom_recommendation, @Param("chairman_remarks") String chairman_remarks, @Param("salient_feature") String salient_feature,  @Param("id") Integer id);

	@Modifying
	@Query(value = "DELETE FROM master.crz_agenda_proposals u WHERE u.agenda_id =?1", nativeQuery = true)
	Integer deleteByAgendaId(Integer id);
	
	@Query(value = "SELECT * FROM master.crz_agenda_proposals prop inner join  master.crz_agenda_mom  mom on prop.agenda_id = mom.agenda_id WHERE  mom.status in ('MomPublish') and prop.mom_recommendation in ('Recommended','Rejected') order by 1 desc", nativeQuery = true)
	List<CrzAgendaProposalsDto> findAllMomListApprovedByChairman();
}
