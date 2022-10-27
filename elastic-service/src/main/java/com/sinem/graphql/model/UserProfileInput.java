package com.sinem.graphql.model;

import lombok.Data;

@Data
public class UserProfileInput {
    Long authid;
    String email;
    String username;
}
