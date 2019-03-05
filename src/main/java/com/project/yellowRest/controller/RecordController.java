package com.project.yellowRest.controller;

import com.project.yellowRest.model.Record;
import com.project.yellowRest.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class RecordController {

    private final RecordService recordService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records")
    public Iterable<Record> showAll(@RequestParam("size") int size, @RequestParam("page") int page) {
        Pageable pageable = new PageRequest(page, size);
        return recordService.findAll(pageable);
    }

    @PostMapping("/records")
    public Record save(
            @RequestBody Record record,
            @RequestBody MultipartFile file
    ) throws IOException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.saveRecord(record, file, uploadPath, email);
    }

    @GetMapping("/records/{id}")
    public Record showOne(@PathVariable Long id) {
        return recordService.findById(id);
    }

    @PutMapping("/records/{id}")
    public Record update(@RequestBody Record record, @PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return recordService.update(record, recordId, email);
    }

    @DeleteMapping("/records/{id}")
    public void delete(@PathVariable("id") Long recordId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        recordService.delete(recordId, email);
    }
}
