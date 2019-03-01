package com.project.yellowRest.util;

import com.project.yellowRest.entity.User;
import com.project.yellowRest.model.UserModel;

public class UserConverterUtils {
    public static UserModel convertToModel(User user){
        UserModel userModel = new UserModel();
        userModel.setActive(user.isActive());
        userModel.setEmail(user.getEmail());
        userModel.setGender(user.getGender());
        userModel.setId(user.getId());
        userModel.setLastVisit(user.getLastVisit());
//        userModel.setRecords(user.getRecords());
        userModel.setUsername(user.getUsername());
        userModel.setUserpic(user.getUserpic());
        userModel.setRoles(user.getRoles());
        return userModel;
    }

    public static User convertToEntity(UserModel userModel){
        User user = new User();
        user.setActive(user.isActive());
        user.setEmail(userModel.getEmail());
        user.setGender(userModel.getGender());
        user.setId(userModel.getId());
        user.setLastVisit(userModel.getLastVisit());
        //user.setRecords(userModel.getRecords());
        user.setUsername(userModel.getUsername());
        user.setUserpic(userModel.getUserpic());
        return user;
    }
}
