package com.project.restapi.api.controllers;

import com.project.restapi.api.models.user.UserLoginModel;
import com.project.restapi.api.models.user.UserLoginResponseModel;
import com.project.restapi.businesslogic.services.interfaces.IAuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final IAuthenticationService authorizationService;

    public AuthenticationController(IAuthenticationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/authenticate")
    public UserLoginResponseModel AuthorizeUser(@RequestBody UserLoginModel userModel){
        return authorizationService.LoginUser(userModel.getLogin(), userModel.getPassword());
    }
}
