package com.project.yellowRest.controller;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserRecordsController {

    private final RecordRepository recordRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserRecordsController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/user-records/{user}")
    @Transactional
    public List<Record> userRecords(
            @PathVariable("user") Long userId
    ) {
        List<Record> recordList = recordRepository.findByUserId(userId);
        return recordList;
    }

    @PutMapping("/user-records/{id}")
    public Record updateRecord(@RequestBody Record record, @PathVariable("id") Record recordFromDb, @AuthenticationPrincipal User user) {
        if (user.getId().equals(recordFromDb.getAuthor().getId())) {
            record.setAuthor(user);
            return recordRepository.save(record);
        }
        return null;

    }

    @DeleteMapping("/user-records/{id}")
    public void delete(@PathVariable("id") Record record, @AuthenticationPrincipal User user) {
        if(user.getId().equals(record.getAuthor().getId()))
            recordRepository.delete(record);
    }
}
