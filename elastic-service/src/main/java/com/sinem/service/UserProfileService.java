package com.sinem.service;

import com.sinem.dto.request.UserProfileRequestDto;
import com.sinem.graphql.model.UserProfileInput;
import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository){
        super(repository);
        this.repository=repository;
    }

    public void save(UserProfileRequestDto dto){
        save(UserProfile.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .avatar(dto.getAvatar())
                .authid(dto.getAuthid())
                .userid(dto.getId())
                .email(dto.getEmail())
                .build());
    }

    public Boolean saveInput(UserProfileInput userProfileInput){
        save(UserProfile.builder()
                .authid(userProfileInput.getAuthid())
                .email(userProfileInput.getEmail())
                .username(userProfileInput.getUsername())
                .build());
        return true;
        /**
         * eğer bu şekilde kayıt işlenecekse
         * gerçek dataların tutulduğu işlemlerin yapıldığı
         * mikroservislere de ilgili kaydın geçilmesi gerekir.
         * bu kısımda manager kullanarak kayıt işlemi yapılabilir
         */
    }
}
