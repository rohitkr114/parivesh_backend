package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAgendaOtherItemsDto;

@Repository
public interface CrzAgendaOtherItemsRepository extends JpaRepository<CrzAgendaOtherItemsDto, Integer> {


	@Query(value = "SELECT * FROM master.crz_agenda_other_items u WHERE u.agenda_id =?1", nativeQuery = true)
	List<CrzAgendaOtherItemsDto> findByAgenda_id(Integer id);
	

	@Modifying
	@Query(value = "DELETE FROM master.crz_agenda_other_items u WHERE u.agenda_id =?1", nativeQuery = true)
	Integer deleteByAgendaId(Integer id);
}
