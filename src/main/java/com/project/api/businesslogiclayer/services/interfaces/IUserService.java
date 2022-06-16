package com.project.api.businesslogiclayer.services.interfaces;

import com.project.api.apilayer.models.UserCreateModel;
import com.project.api.apilayer.models.UserResponseModel;
import com.project.api.apilayer.models.UserUpdateModel;
import javassist.tools.web.BadHttpRequest;

import java.util.List;

public interface IUserService
{
    List<UserResponseModel> GetUsers();

    UserResponseModel GetUser(long userId);

    void CreateUser(UserCreateModel userModel);

    void UpdateUser(long userId, UserUpdateModel userModel);

    void UpdateUserPassword(long userId, String oldPassword, String newPassword, String newPasswordCheck);

    void DeleteUser(long userId);

}
