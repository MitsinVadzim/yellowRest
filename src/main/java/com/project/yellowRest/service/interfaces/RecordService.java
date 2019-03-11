package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.model.Record;

public interface RecordService {
    Record saveRecord(Record record, Long userId);
    Record update(Record record, Long recordId, String email);
    void delete(Long recordId, String email);
}
