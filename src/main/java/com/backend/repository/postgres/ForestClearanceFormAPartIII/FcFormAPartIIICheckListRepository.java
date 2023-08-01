package com.backend.repository.postgres.ForestClearanceFormAPartIII;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.FcFormAPartIII.FcFormAPartIIICheckListDetails;

public interface FcFormAPartIIICheckListRepository  extends JpaRepository<FcFormAPartIIICheckListDetails, Integer> {

}
