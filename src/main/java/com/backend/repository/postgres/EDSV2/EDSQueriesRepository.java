package com.backend.repository.postgres.EDSV2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.EDSQueriesDTO;
import com.backend.model.EDSV2.EDSQueries;

public interface EDSQueriesRepository extends JpaRepository<EDSQueries,Integer> {
	
	@Query(value = "select ec.id, "
			+ "ec.query, ec.description, "
			+ "ec.response, ec.status "
			+ "from authority.eds_queries ec where ec.eds_form_v2_id=?1", nativeQuery = true)
	public List<EDSQueriesDTO> getEdsById(Integer eds_id);
	

    @Query(value = "select ec.query_document_id,ec.response_document_id from authority.eds_queries ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getDocuments(Integer id);
	
	
	
}
