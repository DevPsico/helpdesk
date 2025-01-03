package com.ericson.helpdesk.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;

	@Value("${jwt.secret}")
	private String secret;

	// Classe usada para gerar o TOKEN
	public String generateToken(String email) {
		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}

	public boolean tokenValido(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {

			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			 // Adicionando logs para debug
	        System.out.println("Username: " + username);
	        System.out.println("Token expiration: " + expirationDate);
	        System.out.println("Current time: " + now);

			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;

			}
		}
		return false;
	}

	private Claims getClaims(String token) {

		try {

			//.parseClaimsJwt TROCA por .parseClaimsJws
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

		} catch (io.jsonwebtoken.ExpiredJwtException e) {
	        System.out.println("Token expirado: " + e.getMessage());
	    } catch (io.jsonwebtoken.SignatureException e) {
	        System.out.println("Falha na assinatura: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Erro ao processar o token: " + e.getMessage());
	    }
		return null;
	}

	public String getUsername(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {

			return claims.getSubject();

		}
		return null;
	}
}