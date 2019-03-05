package com.project.yellowRest.repository;

import com.project.yellowRest.entity.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    Page<RecordEntity> findByUserId(Long userId, Pageable pageable);


}
