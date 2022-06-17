package com.project.restapi.businesslogic.services;

import com.project.restapi.api.models.user.UserLoginResponseModel;
import com.project.restapi.businesslogic.common.PasswordHelper;
import com.project.restapi.businesslogic.exceptions.ResourceNotFoundException;
import com.project.restapi.businesslogic.exceptions.UnauthorizedException;
import com.project.restapi.businesslogic.services.interfaces.IAuthenticationService;
import com.project.restapi.businesslogic.services.interfaces.IUserService;
import com.project.restapi.dataaccesslayer.entities.User;
import com.project.restapi.dataaccesslayer.repositories.UserRepository;
import de.mobiuscode.nameof.Name;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final String secret;
    private final String issuer;
    private final String audience;
    private final Integer expirationInDays;
    private final UserRepository repository;

    public AuthenticationService(
            @Value("${spring.security.jwt.secret}") String secret,
            @Value("${spring.security.jwt.issuer}") String issuer,
            @Value("${spring.security.jwt.audience}") String audience,
            @Value("${spring.security.jwt.expirationDateInDays}") Integer expirationInDays,
            UserRepository repository){
        this.secret = secret;
        this.issuer = issuer;
        this.audience = audience;
        this.expirationInDays = expirationInDays;
        this.repository = repository;
    }

    @Override
    public UserLoginResponseModel LoginUser(String login, String password) {
        var user = repository.findAll()
                .stream()
                .filter(x -> x.getUsername().equals(login))
                .findFirst()
                .orElse(null);

        if (user != null) {
            if (PasswordHelper.VerifyPassword(password, user.getPasswordHash())){
                return new UserLoginResponseModel(GenerateJwtToken(login));
            }
            else {
                throw new UnauthorizedException();
            }
        }
        throw new ResourceNotFoundException(User.class.getSimpleName(), Name.of(User.class, User::getUsername), login);
    }

    private String GenerateJwtToken(String login) {
        return Jwts.builder()
                .setId("id00")
                .setSubject(login)
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setAudience(audience)
                .setExpiration(new Date(System.currentTimeMillis() + DaysToMillis(expirationInDays)))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
    private Integer DaysToMillis(Integer daysCount){
        return daysCount * 24 * 60 * 60 * 1000;
    }
}
