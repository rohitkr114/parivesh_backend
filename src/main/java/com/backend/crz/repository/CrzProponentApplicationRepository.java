package com.backend.crz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.ProponentApplications;

@Repository
public interface CrzProponentApplicationRepository extends JpaRepository<ProponentApplications, Integer> {


    @Query(value = "select count(*) from master.proponent_applications where moefcc_file_number =?1 ", nativeQuery = true)
    public Integer checkDuplicateFileNo(String fn);

    @Modifying
    @Query(value = " update master.proponent_applications set moefcc_file_number=:file, last_remarks=:remark, other_property=:other_property where id =:id ", nativeQuery = true)
    Integer updateFileNo(@Param("id") Integer id, @Param("file") String file, @Param("remark") String remark,@Param("other_property") String other_property);
   
    @Query(value = "select proposal_id from master.proponent_applications where proposal_no =?1 ", nativeQuery = true)
    public Integer findProposalId(String proposal_no);
}
