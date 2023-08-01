package com.backend.repository.postgres;

import com.backend.model.FcRecommendedArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FcRecommendedAreaRepository extends JpaRepository<FcRecommendedArea,Integer> {

    @Query("select fc from FcRecommendedArea fc where fc.fc_id=:fc_id")
    public Optional<FcRecommendedArea> getRecommendedArea(@Param(value = "fc_id") Integer fc_id);

}
