package com.project.yellowRest;

import com.project.yellowRest.domain.User;
import com.project.yellowRest.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

//    @Bean
//    public CommandLineRunner setupDefaultUser(UserService service) {
//        return args -> service.addUser(new User(
//                "user",
//                "user",
//                "rafunafiz@webmails.top"
//        ));
//    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
