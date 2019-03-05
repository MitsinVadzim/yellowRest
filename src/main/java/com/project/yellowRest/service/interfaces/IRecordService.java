package com.project.yellowRest.service.interfaces;

import com.project.yellowRest.model.Record;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IRecordService {
    Record saveRecord(Record record, MultipartFile file, String uploadPath, String email) throws IOException;
    Record update(Record record, Long recordId, String email);
    void delete(Long recordId, String email);
}
