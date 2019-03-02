package com.project.yellowRest.controller;

import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.service.RecordService;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
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
    public Iterable<RecordModel> showAll(Pageable pageable) {
        return recordService.findAll(pageable);
    }

    @PostMapping("/records")
    public RecordModel save(
            @RequestBody RecordModel recordModel,
            @RequestBody MultipartFile file
    ) throws IOException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.saveRecord(recordModel, file, uploadPath, email);
    }

    @GetMapping("/records/{id}")
    public RecordModel showOne(@PathVariable Long id) {
        return recordService.getModelById(id);
    }

    @PutMapping("/records/{id}")
    public RecordModel update(@RequestBody RecordModel recordModel, @PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.update(recordModel, recordId, email);
    }

    @DeleteMapping("/records/{id}")
    public void delete(@PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        recordService.delete(recordId, email);
    }
}
