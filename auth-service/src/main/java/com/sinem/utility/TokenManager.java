package com.sinem.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    public String generateToken(Long id){
        Long time=System.currentTimeMillis();

        return "Token:"+time+":"+id;
    }

    /**
     * Token = Token:1666167395:12
     * @param token
     * @return
     */
    public Long getId(String token){
       String[] datas=token.split(":");
       if(datas.length!=3) return null;
       if(!datas[0].equals("Token")) return null;

       Long time=Long.parseLong(datas[1]);
       Long now=System.currentTimeMillis();
       Boolean verifyTime=(now-time-(1000*30))<0;
       if(!verifyTime) return null;
       return Long.parseLong(datas[2]);

    }
}
