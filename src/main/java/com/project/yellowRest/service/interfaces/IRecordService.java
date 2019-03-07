package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.model.Record;

public interface IRecordService {
    Record saveRecord(Record record, String email);
    Record update(Record record, Long recordId, String email);
    void delete(Long recordId, String email);
    Record findRecordByUserId(Long userId, Long recordId);
}
