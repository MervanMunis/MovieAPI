package com.movielist.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movielist.auth.entities.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{
    
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}