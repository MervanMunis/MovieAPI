package com.movielist.auth.services;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class AuthFilterService extends OncePerRequestFilter{

    private final JwtService jwtService;
    
    private final UserDetailsService UserDetailsService;
    
    public AuthFilterService(JwtService jwtService, UserDetailsService UserDetailsService){
        this.jwtService = jwtService;
        this.UserDetailsService = UserDetailsService;
    }
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        String jwt;
        String username;
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return; 
        }
        // Extract JWT token
        jwt = authHeader.substring(7);
        
        // extract username from JWT
        username = jwtService.extractUsername(jwt);
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = UserDetailsService.loadUserByUsername(username);
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);        
    }
    
    
}
