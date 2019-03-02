package com.project.yellowRest.repository;

import com.project.yellowRest.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {

    Page<Record> findByUserId(Long userId, Pageable pageable);
}
