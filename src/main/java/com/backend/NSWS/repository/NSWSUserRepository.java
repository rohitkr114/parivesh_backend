package com.backend.NSWS.repository;

import com.backend.NSWS.model.NSWSUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NSWSUserRepository extends JpaRepository<NSWSUserDetails, Long> {
}
