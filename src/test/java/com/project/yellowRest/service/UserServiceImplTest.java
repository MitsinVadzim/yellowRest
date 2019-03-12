package com.project.yellowRest.service;

import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.model.User;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.util.UserConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest()
@AutoConfigureMockMvc()
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @MockBean
    Pageable pageable;

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void findAll() {
        List<UserEntity> userEntities = Arrays.asList(
                new UserEntity(1L, "Vadim", "admin@gmail.com"),
                new UserEntity(2L, "Michael", "user@gmail.com")
        );
        Page<UserEntity> page = new PageImpl<>(userEntities, pageable, userEntities.size());
        given(userRepository.findAll(pageable)).willReturn(page);
        List<User> users = UserConverter.convertToModel(userEntities);
        Assert.assertEquals(users, userService.findAll(pageable));
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
    }

    @Test
    public void getUserEntityByEmail() {
    }

    @Test
    public void getUserEntityById() {
    }

    @Test
    public void getUserRecords() {
    }
}