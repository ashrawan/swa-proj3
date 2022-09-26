package com.swa.application.service;

import com.swa.application.dto.ApplicationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {
    ResponseEntity<?> save(ApplicationDto applicationDto);

    List<ApplicationDto> findAll();

    ApplicationDto findById(Integer id);

    ApplicationDto update(ApplicationDto applicationDto);

}
