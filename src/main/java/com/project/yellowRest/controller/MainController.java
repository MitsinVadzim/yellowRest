package com.project.yellowRest.controller;

import com.project.yellowRest.domain.Record;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.exception.RecordNotFoundException;
import com.project.yellowRest.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MainController {
    private final RecordRepository recordRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public MainController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/home")
    public String greeting() {
        return "Hello";
    }

    @GetMapping("/records")
    public Iterable<Record> showAll() {
        return recordRepository.findAll();
    }

    @PostMapping("/records")
    public Record add(
            @RequestBody Record record,
            @RequestBody MultipartFile file,
            @AuthenticationPrincipal User user
    ) throws IOException {
        record.setAuthor(user);
        ControllerUtils.saveFile(record, file, uploadPath);
        return recordRepository.save(record);
    }

    @GetMapping("/records/{id}")
    public Record showOne(@PathVariable Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @PutMapping("/records/{id}")
    public Record update(@RequestBody Record record, @PathVariable("id") Record recordFromDb, @AuthenticationPrincipal User user) {
        if (user.getId().equals(recordFromDb.getAuthor().getId())) {
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
