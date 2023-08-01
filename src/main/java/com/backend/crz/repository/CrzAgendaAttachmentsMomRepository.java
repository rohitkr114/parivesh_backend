package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAgendaAttachmentMomDto;

@Repository
public interface CrzAgendaAttachmentsMomRepository extends JpaRepository<CrzAgendaAttachmentMomDto, Integer> {


	@Query(value = "SELECT * FROM master.crz_agenda_mom_attachments u WHERE u.ref_id =?1", nativeQuery = true)
	List<CrzAgendaAttachmentMomDto> findByAgendaId(Integer id);
	

	@Modifying
	@Query(value = "DELETE FROM master.crz_agenda_mom_attachments u WHERE u.ref_id =?1", nativeQuery = true)
	Integer deleteByAgendaId(Integer agenda_id);

}