package com.backend.crz.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAgendaDetailsDto;
import com.backend.model.CrzAgendaMomDto;

@Repository
public interface CrzAgendaMomDetailRepository extends JpaRepository<CrzAgendaMomDto, Integer> {

	@Modifying
	@Query(value = "update master.crz_agenda_mom set status = :status where mom_id =:mom_id", nativeQuery = true)
	public void updateMomStatus(@Param("mom_id") Integer mom_id, @Param("status") String status);

	@Query(value = "SELECT * FROM master.crz_agenda_mom u WHERE u.status ='MomDraft' order by 1 desc", nativeQuery = true)
	Set<CrzAgendaMomDto> findAllDraftMomList();
	
	@Query(value = "SELECT * FROM master.crz_agenda_mom u order by 1 desc", nativeQuery = true)
	Set<CrzAgendaMomDto> findAllMomHistoryList();
	
	@Query(value = "SELECT MAX(mom_id) FROM master.crz_agenda_mom", nativeQuery = true)
	Integer getNewMomId();
	
	@Query(value = "SELECT * FROM master.crz_agenda_mom u WHERE u.status ='MomSubmit' order by 1 desc", nativeQuery = true)
	Set<CrzAgendaMomDto> findAllSubmitMomList();
	
	@Query(value = "SELECT * FROM master.crz_agenda_mom u WHERE u.status in ('ChairmanApproved', 'QueryRaised') order by 1 desc", nativeQuery = true)
	Set<CrzAgendaMomDto> findAllChairmanApprovedMomList();
	
	@Query(value = "SELECT * FROM master.crz_agenda_mom u WHERE u.status ='MomPublish' order by 1 desc", nativeQuery = true)
	Set<CrzAgendaMomDto> findAllPublishMomList();

	@Query(value= "SELECT * FROM master.crz_agenda_mom where status='QueryReplied' order by 1 desc", nativeQuery = true)
	public Set<CrzAgendaMomDto> findAllListOfMomStatusQueryReplied();

	@Modifying
	@Query(value = "update master.crz_agenda_mom set mom_name = :mom_name where mom_id =:mom_id", nativeQuery = true)
	public void updateMomName(@Param("mom_name") String mom_name, @Param("mom_id") Integer mom_id);
}
