package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.ActivityActionMapping;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActivityActionMappingRepository extends JpaRepository<ActivityActionMapping, Integer>{


    @Query(value ="select * from master.activity_action_mapping aam where aam.application_id=?1",nativeQuery = true)
    Optional<ActivityActionMapping> getByApplicationId(Integer clearenceId);
}
