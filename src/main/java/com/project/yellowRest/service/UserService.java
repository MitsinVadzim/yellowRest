package com.project.yellowRest.service;

import com.project.yellowRest.domain.Role;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService{

    private final UserRepository userRepository;


    //private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found!");
//        }
//        return user;
//    }

    public User addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return null;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

//    public void saveUser(User user, String username, Map<String, String> form) {
//        user.setUsername(username);
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toSet());
//        user.getRoles().clear();
//        for (String key : form.keySet()) {
//            if (roles.contains(key)) {
//                user.getRoles().add(Role.valueOf(key));
//            }
//        }
//        userRepository.save(user);
//    }

    public String saveUser(String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return "User exist";
        }else{
            user = new User();
            user.setActive(true);
            user.setEmail(email);
            user.setUsername("Vadim");
            userRepository.save(user);

            return "User not exist";
        }
    }

    public User updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
        }
//        if (!StringUtils.isEmpty(password)) {
//            user.setPassword(passwordEncoder.encode(password));
//        }
        userRepository.save(user);
        return user;
    }
}
