package com.backend.repository.postgres;

import com.backend.model.ProjectProponentIndividual;
import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserIndividualRepository extends JpaRepository<ProjectProponentIndividual, Integer> {

    @Query(value = "Select p from ProjectProponentIndividual p where pan_no = cast(?1 as text)")
    public Optional<ProjectProponentIndividual> findRecordByPan(String pan_no);

    @Query(value = "Select p from ProjectProponentIndividual p where email = cast(?1 as text)")
    public Optional<ProjectProponentIndividual> findRecordByEmail(String email);

    @Query(value = "Select p from ProjectProponentIndividual p where cin_no = cast(?1 as text)")
    public Optional<ProjectProponentIndividual> findRecordByCin(String cin_no);

    @Query(value = "Select p from ProjectProponentIndividual p where name_of_Entity = cast(?1 as text) and state is null")
    public Optional<ProjectProponentIndividual> findRecordByEntity(String entity);

    @Query(value = "Select p from ProjectProponentIndividual p where name_of_Entity = cast(?1 as text) and state = cast(?2 as text)")
    public Optional<ProjectProponentIndividual> findRecordByEntityAndState(String entity, String state);

    @Query(value = " select * from master.check_user_by_pan_and_email(:panNo,:email); ", nativeQuery = true)
    Boolean checkUserByPanAndEmail(String panNo, String email);
}
