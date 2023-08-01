package com.backend.repository.postgres;

import com.backend.model.EcForm12.ECForm12AddendumOfTransferor;
import com.backend.model.EcForm12.ECForm12DetailsOfComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ECForm12AddendumOfTransferorRepository extends JpaRepository<ECForm12AddendumOfTransferor,Integer> {
    @Query("Select em from ECForm12AddendumOfTransferor em where em.ecForm12.id=?1")
    ECForm12AddendumOfTransferor getFormById(Integer id);

}
