package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.NoteSheetDTO;
import com.backend.model.Notesheet;

public interface NotesheetRepository extends JpaRepository<Notesheet, Integer>{


	
}
