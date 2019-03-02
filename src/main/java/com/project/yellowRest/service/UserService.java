package com.project.yellowRest.service;

import com.project.yellowRest.config.oauth.GooglePrincipal;
import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.Role;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.exception.UserNotFoundException;
import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.model.UserModel;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.util.RecordConverterUtils;
import com.project.yellowRest.util.UserConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserService{

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public UserService(UserRepository userRepository, RecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    public Iterable<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public String saveUser(GooglePrincipal principal){
        User userDb = userRepository.findByEmail(principal.getEmail());
        if(userDb != null){
            return "User exist";
        }else{
            userDb = new User();
            userDb.setEmail(principal.getEmail());
            userDb.setActive(true);
            userDb.setUsername(principal.getName());
            userDb.setRoles(Collections.singleton(Role.USER));
            userDb.setUserpic(principal.getPicture());
            userDb.setGender(principal.getGender());
            userDb.setLastVisit(LocalDateTime.now());
            userRepository.save(userDb);
            return "User not exist";
        }
    }

    public UserModel getUserModel(String email){
        return UserConverterUtils.convertToModel(userRepository.findByEmail(email));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public Iterable<RecordModel> getUserRecords(Long userId, Pageable pageable) {
        Page<Record> records = recordRepository.findByUserId(userId, pageable);
        return RecordConverterUtils.convertListToModels(records);
    }
}
