package com.sinem.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Document(indexName = "userprofile")
public class UserProfile {
    @Id
    Long id;
    /**
     * auth servisinden kayıt olan kişinin auth id sini burada eşliyoruz
     */
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String address;
    String avatar;
}
