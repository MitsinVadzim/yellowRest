package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.config.google.GooglePrincipal;
import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.model.Record;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void save(GooglePrincipal googlePrincipal);
    UserEntity getUserEntityByEmail(String email);
    List<Record> getUserRecords(Long userId, Pageable pageable);
    UserEntity getUserEntityById(Long userId);
}
