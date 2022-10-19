package com.sinem.service;

import com.sinem.dto.request.UserProfileSaveRequestDto;
import com.sinem.repository.entity.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository iUserProfileRepository;
    public UserProfileService(IUserProfileRepository iUserProfileRepository) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
    }
    public Boolean save(UserProfileSaveRequestDto dto){
        save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        return true;
    }
}