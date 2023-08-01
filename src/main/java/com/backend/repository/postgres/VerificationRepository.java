package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.User;
import com.backend.model.VerificationToken;

public interface VerificationRepository extends JpaRepository<VerificationToken, Integer> {

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);

}
