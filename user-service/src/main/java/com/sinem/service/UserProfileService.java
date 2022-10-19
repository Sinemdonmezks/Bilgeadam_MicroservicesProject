package com.sinem.service;
import com.sinem.dto.request.UserProfileSaveRequestDto;
import com.sinem.dto.request.UserProfileUpdateRequestDto;
import com.sinem.exception.ErrorType;
import com.sinem.exception.UserServiceException;
import com.sinem.repository.entity.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.utility.ServiceManager;
import com.sinem.utility.TokenManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository iUserProfileRepository;
    private final TokenManager tokenManager;
    public UserProfileService(IUserProfileRepository iUserProfileRepository,
                              TokenManager tokenManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.tokenManager = tokenManager;
    }

    public Boolean save(UserProfileSaveRequestDto dto){
        save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        return true;
    }

    public Boolean update(UserProfileUpdateRequestDto dto){
        Long authid = tokenManager.getId(dto.getToken());
        if(authid == null) throw new UserServiceException(ErrorType.GECERSIZ_TOKEN);
        Optional<UserProfile> userProfile =
                iUserProfileRepository.findOptionalByAuthid(authid);
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        UserProfile profile = userProfile.get();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setAvatar(dto.getAvatar());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        save(profile);
        return true;
    }
}