package com.project.yellowRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
