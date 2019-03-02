package com.project.yellowRest.controller;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserRecordsController {

    private final UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserRecordsController(UserService userService) {
        this.userService = userService;
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
