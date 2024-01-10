package com.example.server.persistence.services;

import com.example.server.persistence.entities.BaseEntity;
import com.example.server.persistence.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    public final BaseRepository baseRepository;

    @Autowired
    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public BaseEntity GetBaseEntityById(Long id){
        return baseRepository.GetBaseEntityById(id);
    }
}
