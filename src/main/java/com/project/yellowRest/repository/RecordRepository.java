package com.project.yellowRest.repository;

import com.project.yellowRest.entity.RecordEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    List<RecordEntity> findByUserId(Long userId, Pageable pageable);

}
