package com.example.server.persistence.services;

import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity GetUserById(Long id){
        return userRepository.GetUserById(id);
    }

    public UserEntity GetUserByUsername(String username){
        return userRepository.GetUserByUsername(username);
    }
}
