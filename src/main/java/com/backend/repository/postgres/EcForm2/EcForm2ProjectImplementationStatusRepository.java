package com.backend.repository.postgres.EcForm2;

import com.backend.model.EcForm2.EcForm2ProjectImplementationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm2ProjectImplementationStatusRepository extends JpaRepository<EcForm2ProjectImplementationStatus,Integer> {

    @Query(value = "select * from master.ec_form_2_project_implementation_status ec where ec.ec_form_2_id=:ecForm2Id",nativeQuery = true)
    Optional<EcForm2ProjectImplementationStatus> getByForm2Id(@Param(value = "ecForm2Id") Integer ecForm2Id);
}
