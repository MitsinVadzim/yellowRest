package com.project.yellowRest.service;

import com.project.yellowRest.entity.RecordEntity;
import com.project.yellowRest.entity.Role;
import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.exception.UserNotFoundException;
import com.project.yellowRest.model.Record;
import com.project.yellowRest.model.User;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.service.interfaces.IService;
import com.project.yellowRest.service.interfaces.IUserService;
import com.project.yellowRest.util.RecordConverter;
import com.project.yellowRest.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService implements IService<User>, IUserService {

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public UserService(UserRepository userRepository, RecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return UserConverter.convertToModel(userRepository.findAll(pageable));
    }

    @Override
    public User findById(Long id) {
        return UserConverter.convertToModel(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public void save(Map<String, ?> userInfo){
        String email = (String)userInfo.get("email");
        UserEntity userEntityDb = userRepository.findByEmail(email);
        if(userEntityDb == null){
            userEntityDb = new UserEntity();
            userEntityDb.setEmail(email);
            userEntityDb.setActive(true);
            userEntityDb.setUsername((String)userInfo.get("given_name"));
            userEntityDb.setRoles(Collections.singleton(Role.USER));
            userEntityDb.setUserpic((String)userInfo.get("picture"));
            userEntityDb.setGender((String)userInfo.get("gender"));
            userEntityDb.setLastVisit(LocalDateTime.now());
            userRepository.save(userEntityDb);
        }
    }

    @Override
    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Record> getUserRecords(Long userId, Pageable pageable) {
        Page<RecordEntity> records = recordRepository.findByUserId(userId, pageable);
        return RecordConverter.convertToModel(records);
    }
}
