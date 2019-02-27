package com.project.yellowRest.controller;

import com.project.yellowRest.domain.User;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Iterable<User> userList() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public User userEditForm(@PathVariable User user) {
        return user;
    }

//    @GetMapping("/profile")
//    public User getProfile(@AuthenticationPrincipal User user) {
//        return user;
//    }
//
//    @PutMapping("/profile")
//    public User updateProfile(
//            @RequestBody User newUser,
//            @AuthenticationPrincipal User user
//    ) {
//        return userService.updateProfile(user, newUser.getEmail());
//    }
}
