package com.project.restapi.businesslogic.mappers;

import com.project.restapi.dataaccesslayer.entities.User;
import com.project.restapi.api.models.UserCreateModel;
import com.project.restapi.api.models.UserResponseModel;
import com.project.restapi.api.models.UserUpdateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper()
    {
        this.modelMapper = new ModelMapper();
    }

    public User MapToEntity(UserCreateModel model)
    {
        return modelMapper.map(model, User.class);
    }

    public User MapToEntity(UserUpdateModel model)
    {
        return modelMapper.map(model, User.class);
    }

    public UserResponseModel MapToResponseModel(User entity)
    {
        return modelMapper.map(entity, UserResponseModel.class);
    }
}