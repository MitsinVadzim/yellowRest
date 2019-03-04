package com.project.yellowRest.controller;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.service.RecordService;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/user-records/{user}")
    @Transactional
    public Iterable<RecordModel> userRecords(
            @PathVariable("user") Long userId,
            Pageable pageable
    ) {
        return userService.getUserRecords(userId, pageable);
    }

    @PutMapping("/user-records/{id}")
    public RecordModel updateRecord(@RequestBody RecordModel recordModel, @PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.update(recordModel, recordId, email);
    }

    @DeleteMapping("/user-records/{id}")
    public void delete(@PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        recordService.delete(recordId, email);
    }
}
