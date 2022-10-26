package com.sinem.utility;

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
        userProfileService.saveAll(userProfiles);
    }
}
