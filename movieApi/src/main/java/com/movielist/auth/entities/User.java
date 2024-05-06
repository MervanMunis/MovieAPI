package com.movielist.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import lombok.Builder;

@Getter
@Entity
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    
    @NotBlank(message = "The name can't be blank!")
    private String name;
    
    @NotBlank(message = "The username can't be blank!")
    @Column(unique = true)
    private String username;
    
    @NotBlank(message = "The email can't be blank!")
    @Column(unique = true)
    @Email(message = "Please enter email in proper format!")
    private String email;
    
    @NotBlank(message = "The password can't be blank!")
    @Size(min = 5, message = "The password must have at least 5 characters!")
    private String password;
    
    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
