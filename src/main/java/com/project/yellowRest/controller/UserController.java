package com.project.yellowRest.controller;

import com.project.yellowRest.model.User;
import com.project.yellowRest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Iterable<User> userList(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userService.findAll(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public User getUser(@PathVariable("user") Long userId) {
        return userService.findById(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{user}")
    public User updateUser(@PathVariable("user") Long userId) {
        return userService.findById(userId);
    }

}
