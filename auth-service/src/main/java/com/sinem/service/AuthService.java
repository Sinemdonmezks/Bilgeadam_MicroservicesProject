package com.sinem.service;

import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.dto.request.UserProfileSaveRequestDto;
import com.sinem.exception.AuthServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.manager.UserProfileManager;
import com.sinem.repository.IAuthRepository;
import com.sinem.repository.entity.Auth;
import com.sinem.repository.enums.Roles;
import com.sinem.utility.ServiceManager;
import com.sinem.utility.TokenManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository repository;
    private final UserProfileManager userProfileManager;
    private final TokenManager tokenManager;

    public AuthService(IAuthRepository repository,UserProfileManager userProfileManager,TokenManager tokenManager) {
        super(repository);
        this.repository = repository;
        this.userProfileManager=userProfileManager;
        this.tokenManager=tokenManager;
    }

    public Boolean save(RegisterRequestDto dto) {
        Auth auth = Auth.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(Roles.ROLE_USER)
                .build();
        if (dto.getAdmincode() != null)
            if (dto.getAdmincode().equals("Adm!n"))
                auth.setRoles(Roles.ROLE_ADMIN);
        save(auth);
        if (auth.getId() != null) {
            userProfileManager.save(UserProfileSaveRequestDto.builder()
                            .authid(auth.getId())
                            .email(auth.getEmail())
                            .username(auth.getUsername())
                    .build());
            return true;
        }
        return false;
    }
    public String doLogin(LoginRequestDto dto){
        Optional<Auth> auth=repository.findOptionalByUsernameAndPassword(
                dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()) throw  new AuthServiceException(ErrorType.LOGIN_ERROR_001);
        return tokenManager.generateToken(auth.get().getId());
    }
}
