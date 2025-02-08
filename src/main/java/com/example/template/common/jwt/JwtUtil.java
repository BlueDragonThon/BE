package com.example.template.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class JwtUtil {


    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Keys.hmacShaKeyFor("dsgasgwewfwerwe353534534rqwfasfs".getBytes());
    }

    /**
     * 🔑 JWT 토큰 생성 (회원 ID 기반)
     */
    public String generateToken(Long memberId) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId)) // 사용자 ID 저장
                .signWith(secretKey, SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    public String getMemberId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
