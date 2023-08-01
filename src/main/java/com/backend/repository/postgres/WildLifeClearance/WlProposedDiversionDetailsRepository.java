package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WlProposedDiversionsDetails;

public interface WlProposedDiversionDetailsRepository extends JpaRepository<WlProposedDiversionsDetails, Integer> {

}
