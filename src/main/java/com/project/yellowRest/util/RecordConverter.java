package com.project.yellowRest.util;

import com.project.yellowRest.entity.RecordEntity;
import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.model.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordConverter {
    public static Record convertToModel(RecordEntity recordEntity){
        Record record = new Record();
        record.setDate(recordEntity.getDate());
        record.setDistance(recordEntity.getDistance());
        record.setId(recordEntity.getId());
        record.setTime(recordEntity.getTime());
        record.setUserId(recordEntity.getUserId());
        return record;
    }

    public static RecordEntity convertToEntity(Record record, UserEntity userEntity){
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setDate(record.getDate());
        recordEntity.setDistance(record.getDistance());
        recordEntity.setId(record.getId());
        recordEntity.setTime(record.getTime());
        recordEntity.setUserId(record.getUserId());
        recordEntity.setAuthor(userEntity);
        return recordEntity;
    }

    public static List<Record> convertToModel(Iterable<RecordEntity> recordsEntity){
        List<Record> records = new ArrayList<>();
        for (RecordEntity recordEntity : recordsEntity) {
            records.add(convertToModel(recordEntity));
        }
        return records;
    }
}
