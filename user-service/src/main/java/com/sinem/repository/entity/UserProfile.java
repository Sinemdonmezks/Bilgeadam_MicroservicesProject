package com.sinem.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Table(name="tbluserprofile")
@Entity
public class UserProfile {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
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
