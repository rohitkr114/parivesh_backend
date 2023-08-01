package com.backend.repository.postgres.Crz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.Crz.CrzDisposal;


public interface CrzDisposalRepository extends JpaRepository<CrzDisposal, Integer>{

	@Query("select new CrzDisposal(id,mode_of_disposal) from CrzDisposal where crz_basic_details_id=?1 and is_deleted= false")
	List<CrzDisposal> getDataByCrzId(Integer id);
	
    @Query(value = " select  cast(master.crz_get_state_office_hierarchy_fnc(:state_code) as varchar)", nativeQuery = true)
    String getOfficeHeirarchy(@Param("state_code") Integer state_code);
}
