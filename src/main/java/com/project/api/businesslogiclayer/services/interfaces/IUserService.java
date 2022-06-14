package com.project.api.businesslogiclayer.services.interfaces;

import com.project.api.apilayer.models.UserCreateModel;
import com.project.api.apilayer.models.UserResponseModel;
import com.project.api.apilayer.models.UserUpdateModel;

import java.util.List;

public interface IUserService
{
    List<UserResponseModel> GetUsers();

    UserResponseModel GetUser(long userId);

    void CreateUser(UserCreateModel userModel);

    void UpdateUser(long userId, UserUpdateModel userModel);

    void DeleteUser(long userId);
}
