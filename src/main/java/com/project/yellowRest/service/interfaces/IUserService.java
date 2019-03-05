package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.model.Record;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {
    void save(Map<String, ?> userInfo);
    UserEntity getUserByEmail(String email);
    List<Record> getUserRecords(Long userId, Pageable pageable);
}
