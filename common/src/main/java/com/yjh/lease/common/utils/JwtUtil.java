package com.yjh.lease.common.utils;

import com.yjh.lease.common.exception.LeaseException;
import com.yjh.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static long tokenExpiration = 60 * 60 * 1000L;
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());

    public static String createToken(Long userId, String username) {
        String token = Jwts.builder().
                setSubject("USER_INFO").
                setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                claim("userId", userId).
                claim("username", username).
                signWith(tokenSignKey).
                compact();
        return token;
    }
    public static Claims parseToken(String token){

        // 判断token是否为空
        if (token==null){
            // 如果token为空，则抛出LeaseException异常，错误码为ADMIN_LOGIN_AUTH
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try{
            // 创建JwtParser对象，设置签名密钥为secretKey
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            // 解析token，并返回解析后的Claims对象
            return jwtParser.parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            // 如果token已过期，则抛出LeaseException异常，错误码为TOKEN_EXPIRED
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            // 如果token无效（其他异常），则抛出LeaseException异常，错误码为TOKEN_INVALID
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

}