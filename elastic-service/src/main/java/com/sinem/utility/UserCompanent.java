package com.sinem.utility;

import com.sinem.dto.request.UserProfileRequestDto;
import com.sinem.manager.IUserProfileManager;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCompanent {
private final IUserProfileManager userProfileManager;
private final UserProfileService userProfileService;
    @PostConstruct
    public void firstRun(){
        List<UserProfile> userProfiles= userProfileManager.userList().getBody();
        userProfiles.forEach(userProfile -> {
            userProfile.setId(null);
            userProfile.setUserid(Long.getLong(userProfile.getId()));
            userProfileService.save(userProfile);
        });
    }
}
