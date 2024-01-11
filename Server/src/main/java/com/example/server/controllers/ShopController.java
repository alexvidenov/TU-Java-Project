package com.example.server.controllers;

import com.example.server.persistence.entities.ShopEntity;
import com.example.server.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController extends BaseController<ShopEntity> {

    ShopController(BaseService<ShopEntity> svc) {
        super(svc);
    }
}

