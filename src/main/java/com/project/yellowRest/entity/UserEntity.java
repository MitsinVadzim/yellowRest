package com.project.yellowRest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    private boolean active;

    private String gender;
    private String userpic;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime lastVisit;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<RecordEntity> records;

    public UserEntity(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString(){
        return "";
    }
}
