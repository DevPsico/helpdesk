package com.ericson.helpdesk.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ericson.helpdesk.dtos.CredenciaisDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			// Verifique se o corpo da requisição contém algo
			if (request.getInputStream().available() == 0) {
				throw new IOException("Request body is empty.");
			}

			// Ler o JSON
			CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

			// Verifique se os campos da credencial não são nulos ou vazios
			if (creds.getEmail() == null || creds.getEmail().isEmpty()) {
				throw new IllegalArgumentException("Email is missing or empty.");
			}

			if (creds.getSenha() == null || creds.getSenha().isEmpty()) {
				throw new IllegalArgumentException("Password is missing or empty.");
			}

			// Crie o token de autenticação
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					creds.getEmail(), creds.getSenha(), new ArrayList<>());

			// Tente autenticar o token
			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			return authentication;

		} catch (JsonProcessingException e) {
			throw new AuthenticationException("Failed to parse credentials: " + e.getMessage()) {
			};
		} catch (IOException e) {
			throw new AuthenticationException("Input stream error: " + e.getMessage()) {
			};
		} catch (IllegalArgumentException e) {
			throw new AuthenticationException("Invalid credentials: " + e.getMessage()) {
			};
		} catch (Exception e) {
			throw new AuthenticationException("Authentication failed: " + e.getMessage()) {
			};
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);

		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader("Authorization", "Bearer " + token);

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	/*
	private CharSequence json() {
		long date = new Date().getTime();
		return "{" + "\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
				+ "\"message\": \"Email ou senha inválidos\", " + "\"path\": \"/login\"}";
	}
	*/
	/*
	private CharSequence json() {
	    long date = new Date().getTime();
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("timestamp", date);
	    jsonResponse.put("status", 401);
	    jsonResponse.put("error", "Não autorizado");
	    jsonResponse.put("message", "Email ou senha inválidos");
	    jsonResponse.put("path", "/login");

	    return jsonResponse.toString(4); // O número 4 especifica a quantidade de espaços para indentação
	}
	*/
	
	private CharSequence json() {
	    long date = new Date().getTime();
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("timestamp", date);
	    jsonResponse.put("status", 401);
	    jsonResponse.put("error", "Não autorizado");
	    jsonResponse.put("message", "Email ou senha inválidos");
	    jsonResponse.put("path", "/login");

	    String jsonResponseString = jsonResponse.toString(4); // Aqui você usa o 4 para indentação
	    System.out.println("Resposta JSON gerada: " + jsonResponseString); // Verifique o JSON no console

	    return jsonResponseString;
	}
}