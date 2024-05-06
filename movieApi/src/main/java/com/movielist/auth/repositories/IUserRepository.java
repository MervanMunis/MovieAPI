package com.movielist.auth.repositories;

import com.movielist.auth.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByEmail(String username);
}
