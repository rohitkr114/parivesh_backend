package com.backend.repository.postgres.EcForm9Repository;

import com.backend.model.EcForm8TransferOfTOR.ECForm8TransferCOP;
import com.backend.model.EcForm9TransferOfEC.EcForm9ProposalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm9ProposalDetailsRepository extends JpaRepository<EcForm9ProposalDetails,Integer> {

    @Query("select em from EcForm9ProposalDetails as em where em.ecForm9TransferOfEC.id=?1")
    public EcForm9ProposalDetails getByEc9Id(Integer id);

}
