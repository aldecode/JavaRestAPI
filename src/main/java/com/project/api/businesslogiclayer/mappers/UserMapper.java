package com.project.api.businesslogiclayer.mappers;

import com.project.api.dataaccesslayer.entities.User;
import com.project.api.apilayer.models.UserCreateModel;
import com.project.api.apilayer.models.UserResponseModel;
import com.project.api.apilayer.models.UserUpdateModel;
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