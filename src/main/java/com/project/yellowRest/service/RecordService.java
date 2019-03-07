package com.project.yellowRest.service;

import com.project.yellowRest.entity.RecordEntity;
import com.project.yellowRest.entity.UserEntity;
import com.project.yellowRest.exception.RecordNotFoundException;
import com.project.yellowRest.model.Record;
import com.project.yellowRest.repository.RecordRepository;
import com.project.yellowRest.service.interfaces.IRecordService;
import com.project.yellowRest.service.interfaces.IService;
import com.project.yellowRest.util.RecordConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RecordService implements IService<Record>, IRecordService {
    private final RecordRepository recordRepository;
    private final UserService userService;

    @Autowired
    public RecordService(RecordRepository recordRepository, UserService userService) {
        this.recordRepository = recordRepository;
        this.userService = userService;
    }

    @Override
    public List<Record> findAll(Pageable pageable){
        Iterable<RecordEntity> recordList = recordRepository.findAll(pageable);
        return RecordConverter.convertToModel(recordList);
    }

    @Override
    public Record findById(Long id) {
        return RecordConverter.convertToModel((recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id))));
    }

    @Override
    @Transactional
    public Record findRecordByUserId(Long userId, Long recordId) {
        UserEntity userEntity = userService.getUserEntityById(userId);
        try {
            RecordEntity recordEntity = userEntity.getRecords().get(recordId.intValue() - 1);
            return RecordConverter.convertToModel(recordEntity);
        }catch (IndexOutOfBoundsException ex){
            throw new RecordNotFoundException(recordId);
        }
    }

    @Override
    public Record saveRecord(Record record, String email) {
        UserEntity userEntity = userService.getUserEntityByEmail(email);
        RecordEntity recordEntity = RecordConverter.convertToEntity(record, userEntity);
        return RecordConverter.convertToModel(recordRepository.save(recordEntity));
    }

    private RecordEntity getEntityById(Long id){
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    private void updateRecordProperties(RecordEntity recordEntity, Record record){
        recordEntity.setDate(record.getDate());
        recordEntity.setDistance(record.getDistance());
        recordEntity.setTime(record.getTime());
    }

    @Override
    public Record update(Record record, Long recordId, String email){
        RecordEntity recordEntity = getEntityById(recordId);
        if(isAuthor(email, recordEntity)){
            updateRecordProperties(recordEntity, record);
            return RecordConverter.convertToModel(recordRepository.save(recordEntity));
        }
        return record;
    }

    private boolean isAuthor(String userEmail, RecordEntity recordEntity){
        UserEntity userEntity = userService.getUserEntityByEmail(userEmail);
        return userEntity.getId().equals(recordEntity.getUserId());
    }

    @Override
    public void delete(Long recordId, String email){
        RecordEntity recordEntity = getEntityById(recordId);
        if(isAuthor(email, recordEntity)){
            recordRepository.delete(recordEntity);
        }
    }
}
