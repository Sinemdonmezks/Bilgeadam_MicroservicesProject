package com.sinem.controller;

import com.sinem.dto.request.UserProfileRequestDto;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sinem.constants.ApiUrls.*;

@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfileRequestDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody UserProfile userProfile){
        userProfileService.save(userProfile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> getall(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

    /**
     * sorgu için girilecek parametreler:
     * ->username
     * ->email
     * ->phone
     * address ve emailinde @gmail olan
     * 10 adet girdi isteyebilirim.
     *
     * geriye dönülecek sonuclar :
     * ->id,username, name, avatar
     * ->id , username
     * ->id,username,phone
     * 20 farklı dönüş tipi olabilir.
     *
     * @return
     */
   // public ResponseEntity<?> findByAll(){
     //   return ResponseEntity.ok(userProfileService.findAll());}


}
