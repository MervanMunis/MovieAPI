package com.movielist.controller;

import com.movielist.auth.entities.RefreshToken;
import com.movielist.auth.entities.User;
import com.movielist.auth.services.AuthService;
import com.movielist.auth.services.JwtService;
import com.movielist.auth.services.RefreshTokenService;
import com.movielist.auth.utils.AuthResponse;
import com.movielist.auth.utils.LoginRequest;
import com.movielist.auth.utils.RefreshTokenRequest;
import com.movielist.auth.utils.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtService){
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        
        RefreshToken refreshToken = refreshTokenService.verfiyRefreshToken(refreshTokenRequest.getRefreshToken());
        User  user = refreshToken.getUser();
        
        String accessToken = jwtService.generateToken(user);
        
        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());
    }
    
}
