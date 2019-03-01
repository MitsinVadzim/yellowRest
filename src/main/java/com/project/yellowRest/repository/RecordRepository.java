package com.project.yellowRest.repository;

import com.project.yellowRest.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUserId(Long userId);
}
