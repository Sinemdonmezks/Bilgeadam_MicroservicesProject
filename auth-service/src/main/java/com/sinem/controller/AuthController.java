package com.sinem.controller;

import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.repository.entity.Auth;
import com.sinem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.sinem.constants.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(DOLOGIN)
    public ResponseEntity<String> dologin(@RequestBody @Valid LoginRequestDto dto) {

        return ResponseEntity.ok(authService.doLogin(dto));
    }


    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto) {
        if(authService.save(dto))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Auth>> getList(){
        return ResponseEntity.ok(authService.findAll());
    }
}
