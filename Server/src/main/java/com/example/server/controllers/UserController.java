package com.example.server.controllers;

import com.example.server.persistence.entities.UserEntity;
import com.example.server.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<UserEntity> {

    @Autowired
    UserController(BaseService<UserEntity> svc) {
        super(svc);
    }
}

