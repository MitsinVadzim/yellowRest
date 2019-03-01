package com.project.yellowRest.controller;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.exception.RecordNotFoundException;
import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.service.RecordService;
import com.project.yellowRest.service.UserService;
import com.project.yellowRest.util.ControllerUtils;
import com.project.yellowRest.util.RecordConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class RecordController {

    private final UserService userService;
    private final RecordService recordService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public RecordController(UserService userService, RecordService recordService) {
        this.userService = userService;
        this.recordService = recordService;
    }

    @GetMapping("/google/login")
    public String login(@RequestParam("code") String code) {
        return "Code =  " + code;
    }

    @GetMapping("/records")
    public Iterable<RecordModel> showAll() {
        return recordService.findAll();
    }

    @PostMapping("/records")
    public Record add(
            @RequestBody RecordModel recordModel,
            @RequestBody MultipartFile file
    ) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUser(email);
        Record record = RecordConverterUtils.convertToEntity(recordModel, user);
        ControllerUtils.saveFile(record, file, uploadPath);
        return recordService.saveRecord(record);
    }

    @GetMapping("/records/{id}")
    public Record showOne(@PathVariable Long id) {
        return
        return recordRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @PutMapping("/records/{id}")
    public Record update(@RequestBody Record record, @PathVariable("id") Record recordFromDb, @AuthenticationPrincipal User user) {
        if (user.getId().equals(recordFromDb.getId())) {
            record.setAuthor(user);
            return recordRepository.save(record);
        }
        return null;
    }

    @DeleteMapping("/records/{id}")
    public void delete(@PathVariable("id") Record record, @AuthenticationPrincipal User user) {
        if (user.getId().equals(record.getAuthor().getId()))
            recordRepository.delete(record);
    }
}
