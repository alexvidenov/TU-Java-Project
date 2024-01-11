package com.example.server.network.controllers;

import com.example.server.network.dtos.ShopDto;
import com.example.server.persistence.entities.ShopEntity;
import com.example.server.services.BaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
public class ShopController extends BaseController<ShopEntity, ShopDto> {

    ShopController(BaseService<ShopEntity> svc) {
        super(svc);
    }

    @Override
    public ShopEntity mapToDbRepresentation(ShopDto dto) {
        final ShopEntity entity = new ShopEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.id = dto.id;
        return entity;
    }

    @Override
    public ShopDto mapToDtoRepresentation(ShopEntity entity) {
        final ShopDto dto = new ShopDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.id = entity.id;
        return dto;
    }
}

