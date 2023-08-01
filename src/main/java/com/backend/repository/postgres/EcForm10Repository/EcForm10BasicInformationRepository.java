package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EcForm10BasicInformationRepository extends JpaRepository<EcForm10BasicInformation, Integer> {

    /*@Query("select em from EcForm10BasicInformation as em where em.ecForm10ProjectDetails.id=?1")
    EcForm10BasicInformation getByEc10Id(Integer id);*/

    @Query("select new EcForm10BasicInformation( ec.id,"
            + " ec.proposal_no ) from EcForm10BasicInformation ec where ec.id=?1")
    public Optional<EcForm10BasicInformation> getFormById(Integer id);

    @Query(value = "select ec.caf_id from master.ec_form10_basic_information ec where ec.id=?1", nativeQuery = true)
    Integer getCafByEcId(Integer id);

}
