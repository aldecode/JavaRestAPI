package com.project.restapi.businesslogic.services.interfaces;

import com.project.restapi.api.models.user.UserCreateModel;
import com.project.restapi.api.models.user.UserResponseModel;
import com.project.restapi.api.models.user.UserUpdateModel;

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
