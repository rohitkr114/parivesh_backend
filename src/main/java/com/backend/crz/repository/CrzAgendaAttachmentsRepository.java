package com.backend.crz.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAgendaAttachmentDto;

@Repository
public interface CrzAgendaAttachmentsRepository extends JpaRepository<CrzAgendaAttachmentDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_agenda_attachments u WHERE u.ref_id =?1", nativeQuery = true)
	List<CrzAgendaAttachmentDto> findByAgendaId(Integer id);
	

	@Modifying
	@Query(value = "DELETE FROM master.crz_agenda_attachments u WHERE u.ref_id =?1", nativeQuery = true)
	Integer deleteByAgendaId(Integer agenda_id);

}
