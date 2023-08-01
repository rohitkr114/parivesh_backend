package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
