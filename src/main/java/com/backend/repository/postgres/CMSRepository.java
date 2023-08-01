package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CMS;


public interface CMSRepository extends JpaRepository<CMS, Integer>{

	@Query("select s from cms s where s.abbr=?1 and is_active='true' and is_delete='false'")
	public CMS findByAbbr(String abbr);
}
