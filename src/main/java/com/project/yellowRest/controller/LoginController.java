package com.project.yellowRest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/google/login")
    public String login(@RequestParam("code") String code) {
        return "Code =  " + code;
    }
}
