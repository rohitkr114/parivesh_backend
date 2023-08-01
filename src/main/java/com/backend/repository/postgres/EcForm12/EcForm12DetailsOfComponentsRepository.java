package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.ECForm12DetailsOfComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EcForm12DetailsOfComponentsRepository extends JpaRepository<ECForm12DetailsOfComponents, Integer> {
    @Query("Select em from ECForm12DetailsOfComponents em where em.ecForm12.id=?1")
    ECForm12DetailsOfComponents getFormById(Integer id);

}
