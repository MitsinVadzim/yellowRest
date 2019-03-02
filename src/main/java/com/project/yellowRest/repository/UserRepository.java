package com.project.yellowRest.repository;

import com.project.yellowRest.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

}
