package com.example.server.persistence.services;

import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Long id){
        return userRepository.getUserById(id);
    }

    public List<UserEntity> getUserByUsername(String username){
        return userRepository.getUserByUsernameContaining(username);
    }
}
