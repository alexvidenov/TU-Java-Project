package com.example.server.persistence.services;

import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.UserRepository;
import com.example.server.services.iServicable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements iServicable<UserEntity> {
    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUserByUsername(String username){
        return userRepository.getUserByUsernameContaining(username);
    }

    @Override
    public UserEntity create(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public UserEntity update(Long id, UserEntity entity) {
        if(entity.id.equals(id)){
            userRepository.save(entity);
        }
        else {
            var targetUser = getById(id);
            targetUser.setUsername(entity.getUsername());
            targetUser.setShoppingCart(entity.getShoppingCart());
            userRepository.save(targetUser);
        }
        return getById(id);
    }

    @Override
    public Boolean delete(Long id) {
        return userRepository.deleteUser(id) == 1;
    }
}
