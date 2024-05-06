package com.movielist.auth.services;

import com.movielist.auth.entities.RefreshToken;
import com.movielist.auth.entities.User;
import com.movielist.auth.repositories.IRefreshTokenRepository;
import com.movielist.auth.repositories.IUserRepository;
import com.movielist.exceptions.TokenExpiredException;
import com.movielist.exceptions.TokenNotFoundException;
import java.time.Instant;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RefreshTokenService {
    
    private final IUserRepository userRepository;
    
    private final IRefreshTokenRepository refreshTokenRepository;
    
    public RefreshTokenService(IUserRepository userRepository, IRefreshTokenRepository refreshTokenRepository){
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    
    public RefreshToken createRefreshToken(String username){
        User user =userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        
        RefreshToken refreshToken = user.getRefreshToken();
        
        if (refreshToken == null) {
            long refreshValidity = 30 * 1000;
                    
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshValidity))
                    .user(user)
                    .build();
            refreshTokenRepository.save(refreshToken);
        }
        
        return refreshToken;
    }
    
    public RefreshToken verfiyRefreshToken(String refreshToken){
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenNotFoundException("Refresh token not found!"));
        
        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refToken);
            throw new TokenExpiredException("Refresh Token expired!");
        }
        
       return refToken; 
    }
}
