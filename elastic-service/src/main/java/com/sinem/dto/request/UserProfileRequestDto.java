package com.sinem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserProfileRequestDto {

    Long id;
    Long  userid;
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;

}
