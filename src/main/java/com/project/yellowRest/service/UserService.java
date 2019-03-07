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
import java.util.stream.Collectors;

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
        return UserConverter.convertToModel(userRepository.findAll(pageable).stream().collect(Collectors.toList()));
    }

    @Override
    public User findById(Long id) {
        return UserConverter.convertToModel(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public void save(Map<String, ?> userInfo){
        String email = (String)userInfo.get("email");
        UserEntity existingUser = userRepository.findByEmail(email);
        if(existingUser == null){
            createUser(userInfo, email);
        }
    }

    private void createUser(Map<String, ?> userInfo, String email) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setActive(true);
        user.setUsername((String)userInfo.get("given_name"));
        user.setRoles(Collections.singleton(Role.USER));
        user.setUserpic((String)userInfo.get("picture"));
        user.setGender((String)userInfo.get("gender"));
        user.setLastVisit(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserEntityByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity getUserEntityById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<Record> getUserRecords(Long userId, Pageable pageable) {
        Page<RecordEntity> records = recordRepository.findByUserId(userId, pageable);
        return RecordConverter.convertToModel(records);
    }
}
