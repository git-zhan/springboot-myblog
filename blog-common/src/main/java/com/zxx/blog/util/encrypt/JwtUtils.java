package com.zxx.blog.util.encrypt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.zxx.blog.util.data.LocalDateTimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtUtils {
	
	private String header;
	
	private Long expiration;
	
	private String secret;
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<String, Object>(2);
        claims.put(Claims.SUBJECT, userName);
        claims.put(Claims.ISSUED_AT, new Date());
        return Jwts.builder().setClaims(claims).setExpiration(new Date(LocalDateTimeUtils.getNowTimestamp() + expiration)).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	public String getUserName(String token) {
		try {
			return getClaimsFromToken(token).getSubject();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isTokenExpired(String token) {
		try {
			return getClaimsFromToken(token).getExpiration().before(new Date());
		} catch (Exception e) {
			return true;
		}
	}
}
