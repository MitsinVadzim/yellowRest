package com.project.yellowRest.controller;

import com.project.yellowRest.domain.User;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal User user) {
        return user;
    }

    @PutMapping("profile")
    public String updateProfile(
            @RequestBody User newUser,
            @AuthenticationPrincipal User user
    ) {
        userService.updateProfile(user, newUser.getPassword(), newUser.getEmail());
        return "redirect:/user/profile";
    }
}
