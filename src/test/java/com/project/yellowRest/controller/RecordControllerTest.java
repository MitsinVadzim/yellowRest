package com.project.yellowRest.controller;

import com.project.yellowRest.model.Record;
import com.project.yellowRest.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc()
@RunWith(SpringJUnit4ClassRunner.class)
public class RecordControllerTest{

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    RecordService recordService;

    @InjectMocks
    RecordController recordController;

    @Test
    public void showAllSuccess() throws Exception {
        List<Record> records = Arrays.asList(
                new Record(1L, 5000, 500D, LocalDate.now(), 1L ),
                new Record(2L, 4000, 20D, LocalDate.now(), 1L));
        Pageable pageable = PageRequest.of(0, 4);
        given(recordService.findAll(pageable)).willReturn(records);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/records").param("page", "0")
                .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
        ;
    }

    @Test
    public void ShowAllWithoutPageableParameters() throws Exception{
        given(recordService.findAll(null)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/records"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void save() {

    }

    @Test
    public void showOne() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

//    @Configuration
//    @Import(Application.class) // the actual configuration
//    public static class ResourceServerConfig extends ResourceServerConfigurerAdapter
//    {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            http.authorizeRequests()
//                    .anyRequest().permitAll();
//            ;
//
//        }
//    }
}
