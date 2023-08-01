package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>{

}
