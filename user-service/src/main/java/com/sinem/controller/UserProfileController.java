package com.sinem.controller;

import com.sinem.dto.request.UserProfileSaveRequestDto;
import com.sinem.dto.request.UserProfileUpdateRequestDto;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sinem.constants.ApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

  /**  @GetMapping("/getupper")
    public ResponseEntity<String> getUpperCasee(String name) {
        return ResponseEntity.ok(userProfileService.getUpperCasee(name));
    }
  */
    @GetMapping("/getupper")
    public ResponseEntity<String> getUpperCase(Long authid) {
        return ResponseEntity.ok(userProfileService.getUpperCase(authid));
    }

    @GetMapping("/savecachable")
    public ResponseEntity<Void> updateUser(@RequestBody UserProfile userProfile){
        userProfileService.updateCacheReset(userProfile);
        return ResponseEntity.ok().build();
    }

/**
 kullanıcı kaydı Auth-service te yapılıyor ve burada User-service e gönderiliyor
 Auth-service ten gelecek bilgiler:
 1-username
2-email
3-authid
@return
 */
    private final UserProfileService userProfileService;
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){

        return ResponseEntity.ok(userProfileService.save(dto));
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.update(dto));
    }
    @PostMapping(FIND_BY_ID)
    public ResponseEntity<UserProfile> findById(Long id){
        return ResponseEntity.ok(userProfileService.findById(id));
    }
    @GetMapping(USER_LIST)
    public ResponseEntity<List<UserProfile>> userList(){

        return ResponseEntity.ok(userProfileService.findAll());
    }
}
