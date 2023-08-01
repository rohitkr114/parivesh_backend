package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.model.CrzQueryDetailsDto;

@Repository
public interface CrzQueryDetailsRepository extends JpaRepository<CrzQueryDetailsDto, Integer>{

	@Query(value = "select * from master.crz_query_details where application_id = ?1", nativeQuery = true )
	List<CrzQueryDetailsDto> findByApplicationId(Integer application_id);

}
