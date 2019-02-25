package com.project.yellowRest.repository;

import com.project.yellowRest.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
