package com.project.yellowRest.util;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.User;
import com.project.yellowRest.model.RecordModel;

public class RecordConverterUtils {
    public static RecordModel convertToModel(Record record){
        RecordModel recordModel = new RecordModel();
        recordModel.setDate(record.getDate());
        recordModel.setDistance(record.getDistance());
        recordModel.setFilename(record.getFilename());
        recordModel.setId(record.getId());
        recordModel.setTime(record.getTime());
        recordModel.setUserId(record.getUserId());
        return recordModel;
    }

    public static Record convertToEntity(RecordModel recordModel, User user){
        Record record = new Record();
        record.setDate(recordModel.getDate());
        record.setDistance(recordModel.getDistance());
        record.setFilename(recordModel.getFilename());
        record.setId(recordModel.getId());
        record.setTime(recordModel.getTime());
        record.setUserId(recordModel.getUserId());
        record.setAuthor(user);
        return record;
    }
}
