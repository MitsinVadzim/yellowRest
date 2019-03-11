package com.project.yellowRest.service.interfaces;


import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityService<T> {
    List<T> findAll(Pageable pageable);

    T findById(Long id);
}
