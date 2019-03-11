package com.project.yellowRest.service;

import com.project.yellowRest.entity.RecordEntity;
import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.model.Record;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.util.RecordConverter;
import org.junit.Assert;
import org.junit.Before;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest()
@AutoConfigureMockMvc()
@RunWith(SpringJUnit4ClassRunner.class)
public class RecordServiceImplTest {

    @MockBean
    Pageable pageable;

    @Autowired
    RecordServiceImpl recordEntityService;

    @MockBean
    RecordRepository recordRepository;

    @MockBean
    UserServiceImpl userService;

    private UserEntity userEntity;

    @Before
    public void init(){
        this.userEntity = new UserEntity(1L, "Vadim", "admin@gmail.com");
    }

    @Test
    public void findAll() {

        List<RecordEntity> recordEntityList = Arrays.asList(
                new RecordEntity(1L, 5000, 500D, LocalDateTime.now(),  userEntity),
                new RecordEntity(2L, 6000, 63D, LocalDateTime.now(), userEntity)
        );
        Page<RecordEntity> page = new PageImpl<>(recordEntityList, pageable, recordEntityList.size());
        given(recordRepository.findAll(pageable)).willReturn(page);
        List<Record> records = recordEntityService.findAll(pageable);
        Assert.assertEquals(2, records.size());

    }

    @Test
    public void findById() {
        RecordEntity recordEntity = new RecordEntity(1L, 5000, 500D, LocalDateTime.now(), userEntity);
        Record record = RecordConverter.convertToModel(recordEntity);
        given(recordRepository.findById(1L)).willReturn(java.util.Optional.of(recordEntity));
        Assert.assertEquals(recordEntityService.findById(1L), record);
    }

    @Test
    public void saveRecord() {
        Record record = new Record(1L, 5000, 500D, LocalDateTime.now(), 1L);
        RecordEntity recordEntity = RecordConverter.convertToEntity(record, userEntity);
        given(userService.getUserEntityById(1L)).willReturn(userEntity);
        given(recordRepository.save(recordEntity)).willReturn(recordEntity);
        Assert.assertEquals(record, recordEntityService.saveRecord(record, 1L));
    }

    @Test
    public void update() {
        Record record = new Record(1L, 5000, 500D, LocalDateTime.now(), 1L);
        Record newRecord = new Record(1L, 6000, 600D, LocalDateTime.now(), 1L);
        RecordEntity recordEntity = RecordConverter.convertToEntity(record, userEntity);
        given(recordRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(recordEntity));
        given(userService.getUserEntityByEmail(userEntity.getEmail())).willReturn(userEntity);
        given(recordRepository.save(recordEntity)).willReturn(recordEntity);
        Assert.assertEquals(newRecord, recordEntityService.update(newRecord, 1L, userEntity.getEmail()));
    }


}
