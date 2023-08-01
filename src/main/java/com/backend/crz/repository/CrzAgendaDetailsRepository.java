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
public interface CrzAgendaDetailsRepository extends JpaRepository<CrzAgendaDetailsDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_agenda_details u WHERE u.status ='Draft' order by 1 desc", nativeQuery = true)
	Set<CrzAgendaDetailsDto> findByStatus();

	@Query(value = "SELECT * FROM master.crz_agenda_details u WHERE u.status ='Submitted' order by 1 desc", nativeQuery = true)
	Set<CrzAgendaDetailsDto> findAgendaListForMom();
	
	@Query(value = "SELECT * FROM master.crz_agenda_details u order by 1 desc", nativeQuery = true)
	Set<CrzAgendaDetailsDto> findAllAgendaHistoryList();
	
	@Modifying
	@Query(value = "update master.crz_agenda_details set status = :status where agenda_id =:agenda_id", nativeQuery = true)
	public void updateAgendaStatus(@Param("agenda_id") Integer agenda_id, @Param("status") String status);

	@Query(value = "SELECT MAX(agenda_id) FROM master.crz_agenda_details", nativeQuery = true)
	Integer getNewAgendaId();

	CrzAgendaDetailsDto save(CrzAgendaDetailsDto entity);
}
