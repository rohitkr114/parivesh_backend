package com.backend.repository.postgres.CrzFactsheet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CrzFactsheet.CrzFactsheet;

public interface CrzFactsheetRepository extends JpaRepository<CrzFactsheet, Integer>{

}
