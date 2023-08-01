package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.UserEmailChangeLog;

public interface UserEmailChangeLogRepository extends JpaRepository<UserEmailChangeLog, Integer> {

}
