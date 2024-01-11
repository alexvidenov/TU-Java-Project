package com.example.server.network.controllers;

import com.example.server.network.dtos.UserDto;
import com.example.server.persistence.entities.UserEntity;
import com.example.server.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<UserEntity, UserDto> {

    @Autowired
    UserController(BaseService<UserEntity> svc) {
        super(svc);
    }

    @Override
    UserEntity mapToDbRepresentation(UserDto dto) {
        final UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setShoppingCart(dto.getShoppingCart());
        return entity;
    }

    @Override
    UserDto mapToDtoRepresentation(UserEntity entity) {
        final UserDto dto = new UserDto();
        dto.setUsername(entity.getUsername());
        dto.setShoppingCart(entity.getShoppingCart());
        return dto;
    }
}

