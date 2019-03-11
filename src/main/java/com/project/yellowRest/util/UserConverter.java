package com.project.yellowRest.util;

import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static User convertToModel(UserEntity userEntity){
        User user = new User();
        user.setActive(userEntity.isActive());
        user.setEmail(userEntity.getEmail());
        user.setId(userEntity.getId());
        user.setLastVisit(userEntity.getLastVisit());
        user.setUsername(userEntity.getUsername());
        user.setUserpic(userEntity.getUserpic());
        user.setRoles(userEntity.getRoles());
        return user;
    }

    public static List<User> convertToModel(List<UserEntity> userEntities){
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(convertToModel(userEntity));
        }
        return users;
    }
}
