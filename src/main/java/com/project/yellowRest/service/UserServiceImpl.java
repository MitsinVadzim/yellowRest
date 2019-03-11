package com.project.yellowRest.service;

import com.project.yellowRest.config.google.GooglePrincipal;
import com.project.yellowRest.entity.RecordEntity;
import com.project.yellowRest.entity.Role;
import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.exception.UserNotFoundException;
import com.project.yellowRest.model.Record;
import com.project.yellowRest.model.User;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.service.interfaces.EntityService;
import com.project.yellowRest.util.RecordConverter;
import com.project.yellowRest.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements EntityService<User>, com.project.yellowRest.service.interfaces.UserService {

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RecordRepository recordRepository) {
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
    public void save(GooglePrincipal googlePrincipal){
        String email = googlePrincipal.getEmail();
        UserEntity existingUser = userRepository.findByEmail(email);
        if(existingUser == null){
            createUser(googlePrincipal, email);
        }else{
            existingUser.setLastVisit(LocalDateTime.now());
        }
    }

    private void createUser(GooglePrincipal googlePrincipal, String email) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setActive(true);
        user.setUsername(googlePrincipal.getGiven_name());
        user.setRoles(Collections.singleton(Role.USER));
        user.setUserpic(googlePrincipal.getPicture());
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
        List<RecordEntity> records = recordRepository.findByUserId(userId, pageable);
        return RecordConverter.convertToModel(records);
    }
}
