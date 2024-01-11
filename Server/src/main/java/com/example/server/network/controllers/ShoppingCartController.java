package com.example.server.network.controllers;

import com.example.server.network.dtos.ShoppingCartDto;
import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.services.BaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController extends BaseController<ShoppingCartEntity, ShoppingCartDto> {

    ShoppingCartController(BaseService<ShoppingCartEntity> svc) {
        super(svc);
    }

    @Override
    ShoppingCartEntity mapToDbRepresentation(ShoppingCartDto dto) {
        final ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setUser(dto.getUser());
        entity.setItems(dto.getItems());
        return entity;
    }

    @Override
    ShoppingCartDto mapToDtoRepresentation(ShoppingCartEntity entity) {
        final ShoppingCartDto dto = new ShoppingCartDto();
        dto.setUser(entity.getUser());
        dto.setItems(entity.getItems());
        return dto;
    }
}

