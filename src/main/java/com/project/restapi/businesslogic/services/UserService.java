package com.project.restapi.businesslogic.services;

import com.project.restapi.businesslogic.exceptions.BadRequestException;
import com.project.restapi.businesslogic.exceptions.ResourceNotFoundException;
import com.project.restapi.businesslogic.common.PasswordHelper;
import com.project.restapi.businesslogic.exceptions.UnauthorizedException;
import com.project.restapi.businesslogic.mappers.UserMapper;
import com.project.restapi.api.models.UserCreateModel;
import com.project.restapi.api.models.UserResponseModel;
import com.project.restapi.api.models.UserUpdateModel;
import com.project.restapi.businesslogic.services.interfaces.IUserService;
import com.project.restapi.dataaccesslayer.entities.User;
import com.project.restapi.dataaccesslayer.repositories.UserRepository;
import de.mobiuscode.nameof.Name;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService
{
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper)
    {
        this.repository = userRepository;
        this.mapper = mapper;
    }

    public List<UserResponseModel> GetUsers()
    {
        return repository.findAll()
                .stream()
                .map(mapper::MapToResponseModel)
                .collect(Collectors.toList());
    }

    public UserResponseModel GetUser(long userId)
    {
        var userToFind = repository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException(User.class.getSimpleName(), Name.of(User.class, User::getId), userId));
        return mapper.MapToResponseModel(userToFind);
    }

    public void CreateUser(UserCreateModel userModel)
    {
        var userToCreate = mapper.MapToEntity(userModel);
        userToCreate.setNormalizedEmail(userModel.getEmail().toUpperCase(Locale.ROOT));
        userToCreate.setNormalizedUserName(userModel.getUsername().toUpperCase(Locale.ROOT));
        userToCreate.setRegistrationTime(Instant.now());
        userToCreate.setPasswordHash(PasswordHelper.HashPassword(userModel.getPassword()));
        repository.save(userToCreate);
    }

    public void UpdateUser(long userId, UserUpdateModel userModel)
    {
        var user = repository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException(User.class.getSimpleName(), Name.of(User.class, User::getId), userId));

            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            user.setUsername(userModel.getUsername());

        repository.save(user);
    }


    public void UpdateUserPassword(long userId, String oldPassword, String newPassword, String newPasswordCheck) {
        if (newPassword.equals(newPasswordCheck)){
            var user = repository.findById(userId).orElseThrow(()
                    -> new ResourceNotFoundException(User.class.getSimpleName(), Name.of(User.class, User::getId), userId));
            if (PasswordHelper.VerifyPassword(oldPassword, user.getPasswordHash())){
                user.setPasswordHash(PasswordHelper.HashPassword(newPassword));
                repository.save(user);
            }
            else {
                throw new UnauthorizedException();
            }
        }
        else {
            throw new BadRequestException("New password in both fields should be equal");
        }
    }

    public void DeleteUser(long userId)
    {
        repository.deleteById(userId);
    }
}
