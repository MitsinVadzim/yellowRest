package com.project.yellowRest.controller;

import com.project.yellowRest.domain.Record;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
public class UserRecordsController {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserRecordsController(RecordRepository recordRepository, UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user-records/{user}")
    @Transactional
    public List<Record> userRecords(
            @PathVariable("user") Long userId
    ) {
        //return recordRepository.findAll(user.getRecordsId());
        //return user.getRecordsId();
        User user = userRepository.findById(userId).get();
        //System.out.println("UserId: "+ userId+ "user: "+ user);
        List<Record> records = user.getRecords();
        System.out.println("Hi");
        return records;
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
