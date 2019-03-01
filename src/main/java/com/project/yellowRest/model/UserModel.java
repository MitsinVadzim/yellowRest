package com.project.yellowRest.model;

import com.project.yellowRest.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserModel {

    @Null
    private Long id;

    private String username;

    private boolean active;

    private String gender;

    private String userpic;

    private LocalDateTime lastVisit;

    @Email
    private String email;

    private Set<Role> roles;

}
