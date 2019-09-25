package com.filipelins.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipelins.cursomc.security.JWTUtil;
import com.filipelins.cursomc.security.UserSS;
import com.filipelins.cursomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS userSS = UserService.authenticated();
		String strToken = jwtUtil.generateToken(userSS.getUsername());
		response.addHeader("Authorization", "Bearer " + strToken);
		return ResponseEntity.noContent().build();
	}
}
