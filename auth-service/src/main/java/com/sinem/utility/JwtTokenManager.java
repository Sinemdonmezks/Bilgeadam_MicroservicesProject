package com.sinem.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sinem.exception.ErrorType;
import com.sinem.exception.AuthServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    @Value("${myjwt.secretkey}")
    private String secretKey;
    @Value("${myjwt.audience}")
    private String audience;
    @Value("${myjwt.issuer}")
    private String issuer;
    public String createToken(Long authid){
        String token=null;
        try{
            /**
             * token yayımcısı kim-> bilgeadamAuth
             * token üretim tarihi->
             * token ne kadar süre geçerli
             * token içerisine tekrar kullanmak için "Claim" nesnesi koyulabilir,
             * bu nesneler key-value şeklinde değer (kişinin profil id gibi) tutar-public olarak görüntülenebilir.
             * token bilgisi şifrelenmelidir.bu nedenle imza yöntemi secilmeli ve
             * belirtilen algoritme ile sign işlemi yapılmalıdır.
             */
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            token= JWT.create()
                    .withAudience(audience)//Kitle
                    .withIssuer(issuer)//yayımcı
                    .withIssuedAt(new java.util.Date())//olusturulma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60)))//geçersiz kalma zamanı
                    .withClaim("authid",authid)//kullanılacak bilgiler
                    .sign(algorithm);//şifreleme-imzalama işlemi
            return token;
        }catch (Exception e){
            return null;
        }
    }

    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256("PLT-7lZhbi|qm}{G*-&(ZRfUKnm7>gPEBA?;Y`~*FB._?Xdp+Z>m,`+##a*'!=@qb");
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withAudience("bilgeadamAuth")//Kitle
                    .withIssuer("Bilgeadam")//yayımcı
                    .build();
          DecodedJWT decodedToken= jwtVerifier.verify(token);//token geçerliliği doğrulanır
            if(decodedToken==null) throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
            Long authid=decodedToken.getClaim("authid").asLong();
            return Optional.of(authid);
        }catch (Exception e){
            throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
        }

    }

}
