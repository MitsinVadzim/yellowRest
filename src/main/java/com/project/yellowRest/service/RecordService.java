package com.project.yellowRest.service;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.exception.RecordNotFoundException;
import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.util.ControllerUtils;
import com.project.yellowRest.util.RecordConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserService userService;

    @Autowired
    public RecordService(RecordRepository recordRepository, UserService userService) {
        this.recordRepository = recordRepository;
        this.userService = userService;
    }

    public Iterable<RecordModel> findAll(Pageable pageable){
        Iterable<Record> recordList = recordRepository.findAll(pageable);
        return RecordConverterUtils.convertListToModels(recordList);
    }

    public RecordModel saveRecord(RecordModel recordModel, MultipartFile file, String uploadPath, String email) throws IOException {
        User user = userService.getUserByEmail(email);
        Record record = RecordConverterUtils.convertToEntity(recordModel, user);
        ControllerUtils.saveFile(record, file, uploadPath);
        return RecordConverterUtils.convertToModel(recordRepository.save(record));
    }

    public RecordModel getModelById(Long id){
        return RecordConverterUtils.convertToModel(recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public Record getEntityById(Long id){
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void updateRecordProperties(Record record, RecordModel recordModel){
        record.setDate(recordModel.getDate());
        record.setDistance(recordModel.getDistance());
        record.setFilename(recordModel.getFilename());
        record.setTime(recordModel.getTime());
    }

    public RecordModel update(RecordModel recordModel, Long recordId, String email){
        Record record = getEntityById(recordId);
        if(isAuthor(email, record)){
            updateRecordProperties(record, recordModel);
            return RecordConverterUtils.convertToModel(recordRepository.save(record));
        }
        return recordModel;
    }

    public boolean isAuthor(String userEmail, Record record){
        User user = userService.getUserByEmail(userEmail);
        return user.getId().equals(record.getUserId());
    }

    public void delete(Long recordId, String email){
        Record record = getEntityById(recordId);
        if(isAuthor(email, record)){
            recordRepository.delete(record);
        }
    }
}
