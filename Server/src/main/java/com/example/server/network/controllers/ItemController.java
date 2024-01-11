package com.example.server.network.controllers;

import com.example.server.network.dtos.ItemDto;
import com.example.server.persistence.entities.ItemEntity;
import com.example.server.services.BaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController extends BaseController<ItemEntity, ItemDto> {
    ItemController(BaseService<ItemEntity> svc) {
        super(svc);
    }

    @Override
    public ItemEntity mapToDbRepresentation(ItemDto dto) {
        ItemEntity item = new ItemEntity();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setShop(dto.getShop());
        item.id = dto.id;
        return item;
    }

    @Override
    public ItemDto mapToDtoRepresentation(ItemEntity entity) {
        ItemDto dto = new ItemDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setShop(entity.getShop());
        dto.id = entity.id;
        return dto;
    }
}

