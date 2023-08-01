package com.backend.repository.postgres.Crz;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.Crz.CrzBasicDetails;;

public interface CrzBasicDetailsRepository extends JpaRepository<CrzBasicDetails, Integer> {
	
	@Modifying
    @Query(value = "Update master.crz_basic_details set proposal_no =?2 where id =?1", nativeQuery = true)
    public Integer updateCrzProposal(Integer id, String proposal_no);
   
    @Modifying
    @Query(value = "Update master.ec_partC set proposal_no =?2 where id =?1", nativeQuery = true)
    public Integer updateEcPartCProposal(Integer id, String proposal_no);
    
    @Query("select cbd from CrzBasicDetails cbd where id=:id")
    public Optional<CrzBasicDetails> findFormById(@Param(value="id")Integer id);
}
