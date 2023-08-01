package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.CAFKMLPlots;

@Repository
public interface CAFKMLPlotsRepository extends JpaRepository<CAFKMLPlots, Integer>{

}
