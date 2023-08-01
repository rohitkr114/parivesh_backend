package com.backend.repository.postgres.Agenda;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Agenda.AgendaRemarks;

public interface AgendaRemarksRepository extends JpaRepository<AgendaRemarks, Integer>{

}
