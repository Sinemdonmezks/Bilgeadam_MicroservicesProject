package com.sinem.service;
import com.sinem.dto.request.UserProfileSaveRequestDto;
import com.sinem.dto.request.UserProfileUpdateRequestDto;
import com.sinem.exception.ErrorType;
import com.sinem.exception.UserServiceException;
import com.sinem.manager.ElasticSearchManager;
import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.utility.JwtTokenManager;
import com.sinem.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager jwttokenManager;
    private final CacheManager cacheManager;
    private final ElasticSearchManager elasticSearchManager;
    public UserProfileService(IUserProfileRepository iUserProfileRepository,
                              JwtTokenManager jwttokenManager,CacheManager cacheManager,
                              ElasticSearchManager elasticSearchManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.jwttokenManager = jwttokenManager;
        this.cacheManager=cacheManager;
        this.elasticSearchManager=elasticSearchManager;
    }

   /* @Cacheable(value = "uppercasee")
    public String getUpperCasee(String name) {
        /**
         * Bu kısım methodun belli işlem basamaklarını simüle etmek ve
         * belli zaman alacak işlemleri göstermek için yazılmıştır.
         */
      /*  try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
        return name.toUpperCase();
    }
    */



    @Cacheable(value = "uppercase")
    public String getUpperCase(Long authid) {
        /**
         * Bu kısım methodun belli işlem basamaklarını simüle etmek ve
         * belli zaman alacak işlemleri göstermek için yazılmıştır.
         */
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
         Optional<UserProfile> user=iUserProfileRepository.findOptionalByAuthid(authid);
        if(user.isEmpty()) return "";
        return user.get().getName().toUpperCase();
    }

    public Boolean save(UserProfileSaveRequestDto dto){
        UserProfile userProfile=save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        elasticSearchManager.save(userProfile);
        return true;
    }

    public Boolean update(UserProfileUpdateRequestDto dto){
       Optional<Long>  authid = jwttokenManager.getByIdFromToken(dto.getToken());
        if(authid.isEmpty()) throw new UserServiceException(ErrorType.GECERSIZ_ID);
        Optional<UserProfile> userProfile =
                iUserProfileRepository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        UserProfile profile = userProfile.get();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setAvatar(dto.getAvatar());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        save(profile);
        elasticSearchManager.save(profile);
        return true;
    }

    public void updateCacheReset(UserProfile profile){
        save(profile);
        /**
         * bu işlem ilgili method tarafından tutulan
         * tüm önbelleklenmiş datayı temizler  çok istemediğimiz gerekli olduğunda kullanılması gereken bir yapı
         * cacheManager.getCache("uppercase") <-bu
         */
        cacheManager.getCache("uppercase").evict(profile.getAuthid());
    }
}