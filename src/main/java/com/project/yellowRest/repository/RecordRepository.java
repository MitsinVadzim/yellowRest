package com.project.yellowRest.repository;

import com.project.yellowRest.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RecordRepository extends JpaRepository<Record, Long> {
//    List<Record> findByRecordsIdIn(Set<Long> recordIdList);
    //List<Record> findAll(Set<Long> recordList);
}
