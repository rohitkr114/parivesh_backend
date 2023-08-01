package com.backend.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.backend.dto.UserPrincipal;


public class AuditorAwareImpl implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
        	return Optional.of(1);
        } else {
        	UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        	return Optional.of(principal.getId());
        }
        
    }
}