package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CafKML;


@Repository
public interface CafKMLRepository extends JpaRepository<CafKML, Integer>{

	@Query("select c from CafKML c where c.commonFormDetail.id=?1 and is_deleted='false'")
	public List<CafKML> findcafKMLbyCafID(Integer id);
	
}
