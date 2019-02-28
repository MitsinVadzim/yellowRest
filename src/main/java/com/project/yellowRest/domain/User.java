package com.project.yellowRest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

//@RequiredArgsConstructor
@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    private String username;

//    @NotBlank(message = "Password cannot be empty")
//    private String password;

    private boolean active;

    private String gender;
    private String userpic;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime lastVisit;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    //@JsonIgnore
    //@JsonBackReference
    private List<Record> records;

    public User(String username, String email) {
        this.username = username;
        //this.password = password;
        this.email = email;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isActive();
//    }

    @Override
    public String toString(){
        return "";
    }
}
