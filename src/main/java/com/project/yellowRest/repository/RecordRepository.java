package com.project.yellowRest.repository;

import com.project.yellowRest.entity.Record;
import com.project.yellowRest.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {

    Page<Record> findByUserId(Long userId, Pageable pageable);


}
