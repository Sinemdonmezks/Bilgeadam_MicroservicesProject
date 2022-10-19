package com.sinem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequestDto {
    @NotNull(message = "Kullanıcı adı girilmelidir")
    @Size(min=3,max=16)
    String username;
    @NotNull(message = "şifre girilmelidir")
    @Size(min=8,max=64)
    String password;
}
