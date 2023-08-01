package com.backend.security;

import com.backend.dto.UserPrincipal;
import com.backend.model.User;
import com.backend.repository.postgres.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findRecordByEmail(username).get(0);
        User user = userRepository.findById(Integer.parseInt(username)).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserByEmail(String username) {
        User user = userRepository.findRecordByEmailId(username).get(0); // orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
        if (user != null) {
            return UserPrincipal.create(user);
        } else {
            return null;
        }
    }
}