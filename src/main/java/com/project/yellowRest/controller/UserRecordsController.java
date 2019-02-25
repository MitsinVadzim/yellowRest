package com.project.yellowRest.controller;

import com.project.yellowRest.domain.Record;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public List<Record> userRecords(
            @PathVariable User user
    ) {
        return user.getRecords();
    }

    @PutMapping("/user-records/{id}")
    public Record updateRecord(@RequestBody Record record, @PathVariable("id") Record recordFromDb, @AuthenticationPrincipal User user) {
        if (user.getId().equals(recordFromDb.getUserId())) {
            record.setUserId(user.getId());
            return recordRepository.save(record);
        }
        return null;

    }

    @DeleteMapping("/user-records/{id}")
    public void delete(@PathVariable("id") Record record, @AuthenticationPrincipal User user) {
        if(user.getId().equals(record.getUserId()))
            recordRepository.delete(record);
    }
}
