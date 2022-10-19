package com.sinem.manager;

import com.sinem.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sinem.constants.ApiUrls.SAVE;

/**
 * name benzersiz olamlıdır
 */
@FeignClient(name = "user-profile-service",
        url= "http://localhost:9092/api/v1/user",
        decode404 = true)
public interface UserProfileManager {

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);


    }
