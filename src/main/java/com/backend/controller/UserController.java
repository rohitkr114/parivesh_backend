package com.backend.controller;

import com.backend.dto.UserPrincipal;
import com.backend.model.User;
import com.backend.security.CurrentUser;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getLoggerInUser")
    public User getLoggedInUser(@CurrentUser UserPrincipal principal) {
        return userService.getLoggedInUser(principal.getId());
    }

    @PostMapping("/getOrganisation")
    public ResponseEntity<Object> getKycDetail(@CurrentUser UserPrincipal principal) {
        return userService.getOrganisationDetail(principal);
    }

    @PostMapping("/getSelectedRoleName")
    public ResponseEntity<Object> getSelectedRoleName(@CurrentUser UserPrincipal principal) {

        return userService.getSelectedRoleName(principal.getId());
    }

    @PostMapping("/getSelectedSector")
    public ResponseEntity<Object> getSelectedSector(@CurrentUser UserPrincipal principal) {

        return userService.getSelectedSector(principal.getId());
    }
}
