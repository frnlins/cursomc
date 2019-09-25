package com.filipelins.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String email) {
		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean isTokenValid(String strToken) {
		Claims claims = getClaims(strToken);

		if (claims != null) {
			String username = claims.getSubject();
			Date expirantionDate = claims.getExpiration();
			Date dateNow = new Date(System.currentTimeMillis());

			if (username != null && expirantionDate != null && dateNow.before(expirantionDate)) {
				return true;
			}
		}

		return false;
	}

	private Claims getClaims(String strToken) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(strToken).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String strToken) {
		Claims claims = getClaims(strToken);

		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
}
