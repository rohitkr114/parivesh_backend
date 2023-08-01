package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAgendaParticipantDto;

@Repository
public interface CrzAgendaParticipantsRepository extends JpaRepository<CrzAgendaParticipantDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_agenda_participant u WHERE u.agenda_id =?1 order by 1 asc", nativeQuery = true)
	List<CrzAgendaParticipantDto> findByAgenda_id(Integer id);

	@Modifying
	@Query(value = "update master.crz_agenda_participant set remarks = :remarks where id =:id", nativeQuery = true)
	public void updateAgendaParticipantRemarks(@Param("id") Long id, @Param("remarks") String status);

	@Modifying
	@Query(value = "DELETE FROM master.crz_agenda_participant u WHERE u.agenda_id =?1", nativeQuery = true)
	Integer deleteByAgendaId(Integer id);

}
