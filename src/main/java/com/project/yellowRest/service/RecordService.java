package com.project.yellowRest.service;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.model.RecordModel;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.util.RecordConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<RecordModel> findAll(){
        List<Record> recordList = recordRepository.findAll();
        List<RecordModel> recordModelList = new ArrayList<>();
        for (Record record : recordList) {
            recordModelList.add(RecordConverterUtils.convertToModel(record));
        }
        return recordModelList;
    }

    public Record saveRecord(Record record){
        return recordRepository.save(record);
    }

    public Record
}
