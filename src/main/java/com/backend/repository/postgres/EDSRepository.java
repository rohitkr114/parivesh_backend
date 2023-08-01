package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.EDS;

public interface EDSRepository extends JpaRepository<EDS, Integer>{

}
