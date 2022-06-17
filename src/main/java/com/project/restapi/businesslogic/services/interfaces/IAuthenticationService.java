package com.project.restapi.businesslogic.services.interfaces;

import com.project.restapi.api.models.user.UserLoginResponseModel;

public interface IAuthenticationService {
    UserLoginResponseModel LoginUser(String login, String password);
}
