package com.swa.apply.service;

import com.swa.apply.dto.ApplicationDto;

import java.util.List;

public interface ApplicationService {
    ApplicationDto save(ApplicationDto applicationDto);

    List<ApplicationDto> findAll();

    ApplicationDto findById(Integer id);

    ApplicationDto update(ApplicationDto applicationDto);
}
