package com.project.yellowRest.controller;

import com.project.yellowRest.model.Record;
import com.project.yellowRest.service.RecordService;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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

    @GetMapping("/users/{userid}/records")
    @Transactional
    public List<Record> userRecords(
            @PathVariable("userid") Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return userService.getUserRecords(userId, pageable);
    }

    @PutMapping("/users/{userid}/records/{recordid}")
    public Record updateRecord(@RequestBody Record recordModel,
                               @PathVariable("recordid") Long recordId,
                               @PathVariable("userid") Long userId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.update(recordModel, recordId, email);
    }

    @DeleteMapping("/user-records/{id}")
    public void delete(@PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        recordService.delete(recordId, email);
    }
}
