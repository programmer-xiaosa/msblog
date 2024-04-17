package com.arnasoft.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class TokenValidTest {
    // 过期时间
    public static final long EXPIRITION = 1000 * 60;

    @Test
    public void testGenerate(){

        String compact = Jwts.builder()
                .setId(UUID.randomUUID().toString())//设置唯一标识
                .setSubject("NINECLOCK") //设置主题
                .claim("name", "musa") //自定义信息
                .claim("age", 31) //自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION)) //设置过期时间
                .setIssuedAt(new Date()) //令牌签发时间
                .signWith(SignatureAlgorithm.HS256, "itheima")
                .compact();//签名算法, 秘钥
        System.out.println(compact);

    }


    @Test
    public void testVerify(){
        try {
            String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjNTkzMmUyZC00ZjU1LTRlYjItOWQ3Zi00MThlNThjZmVmZDUiLCJzdWIiOiJOSU5FQ0xPQ0siLCJuYW1lIjoibXVzYSIsImFnZSI6MzEsImV4cCI6MTcwOTAzOTE2OCwiaWF0IjoxNzA5MDM5MTA4fQ.bBopvJ8ZHQ5KSTV9CYAxJVUcjLlOvOeihb-xYRJOQds";
            Claims claims = Jwts.parser().setSigningKey("itheima").parseClaimsJws(jwt).getBody();
            System.out.println(claims);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
