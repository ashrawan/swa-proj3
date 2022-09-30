package com.swa.application.service;

import com.swa.application.dto.ApplicationDto;
import com.swa.application.response.ServerResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {
    ServerResponse save(ApplicationDto applicationDto);
    List<ApplicationDto> findAll();

    ApplicationDto findById(String id);

    ApplicationDto update(ApplicationDto applicationDto);

}
