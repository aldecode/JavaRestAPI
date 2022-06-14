package com.project.api.businesslogiclayer.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.project.api.businesslogiclayer.mappers.UserMapper;
import com.project.api.apilayer.models.UserCreateModel;
import com.project.api.apilayer.models.UserResponseModel;
import com.project.api.apilayer.models.UserUpdateModel;
import com.project.api.businesslogiclayer.services.interfaces.IUserService;
import com.project.api.dataaccesslayer.entities.User;
import com.project.api.dataaccesslayer.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService
{
    private final IUserRepository IUserRepository;
    private final UserMapper mapper;

    public UserService(IUserRepository userRepository, UserMapper mapper)
    {
        this.IUserRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserResponseModel> GetUsers()
    {
        return IUserRepository.findAll()
                .stream()
                .map(mapper::MapToResponseModel)
                .collect(Collectors.toList());
    }

    public UserResponseModel GetUser(long userId)
    {
        return mapper.MapToResponseModel(IUserRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User with ID %d not exist.", userId))));
    }

    public void CreateUser(UserCreateModel userModel)
    {
        User userToCreate = mapper.MapToEntity(userModel);
        userToCreate.setNormalizedEmail(userModel.getEmail().toUpperCase(Locale.ROOT));
        userToCreate.setNormalizedUserName(userModel.getUsername().toUpperCase(Locale.ROOT));
        userToCreate.setRegistrationTime(Instant.now());
        userToCreate.setPasswordHash(BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()));
        IUserRepository.save(userToCreate);
    }

    public void UpdateUser(long userId, UserUpdateModel userModel)
    {
        var user = IUserRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User1 with ID %d not exist.", userId)));

        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setUsername(userModel.getUsername());

        IUserRepository.save(user);
    }

    public void DeleteUser(long userId)
    {
        IUserRepository.deleteById(userId);
    }
}
