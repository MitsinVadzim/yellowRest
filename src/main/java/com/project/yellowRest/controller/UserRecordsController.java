package com.project.yellowRest.controller;

import com.project.yellowRest.model.Record;
import com.project.yellowRest.service.RecordService;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class UserRecordsController {

    private final UserService userService;
    private final RecordService recordService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserRecordsController(UserService userService, RecordService recordService) {
        this.userService = userService;
        this.recordService = recordService;
    }

    @GetMapping("/user-records/{user}")
    @Transactional
    public Iterable<Record> userRecords(
            @PathVariable("user") Long userId,
            Pageable pageable
    ) {
        return userService.getUserRecords(userId, pageable);
    }

    @PutMapping("/user-records/{id}")
    public Record updateRecord(@RequestBody Record recordModel, @PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.update(recordModel, recordId, email);
    }

    @DeleteMapping("/user-records/{id}")
    public void delete(@PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        recordService.delete(recordId, email);
    }
}
