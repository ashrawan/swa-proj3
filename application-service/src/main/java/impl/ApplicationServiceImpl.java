//package com.swa.apply.service.impl;
//
//import com.swa.apply.dto.ApplicationDto;
//import com.swa.apply.dto.mapper.ApplicationMapper;
//import com.swa.apply.model.Application;
//import com.swa.apply.repository.ApplicationRepository;
//import com.swa.apply.service.ApplicationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ApplicationServiceImpl implements ApplicationService {
//
//    @Autowired private ApplicationMapper mapper;
//    @Autowired private ApplicationRepository applicationRepository;
//
//    @Override
//    public ApplicationDto save(ApplicationDto applicationDto) {
//        Application application = mapper.mapToApplication(applicationDto);
//        return mapper.mapToDto(applicationRepository.save(application));
//    }
//
//    @Override
//    public List<ApplicationDto> findAll() {
//        List<ApplicationDto> applicationDtos = new ArrayList<>();
//        List<Application> applications = applicationRepository.findAll();
//        for (Application application: applications) {
//            ApplicationDto applicationDto = mapper.mapToDto(application);
//            applicationDtos.add(applicationDto);
//        }
//        return applicationDtos;
//    }
//
//    @Override
//    public ApplicationDto findById(Integer id) {
//        Optional<Application> applicationOpt = applicationRepository.findById(id);
//        if(!applicationOpt.isPresent()){
//            System.out.println("Application not found!");
//            return null;
//        }
//        return mapper.mapToDto(applicationOpt.get());
//    }
//
//    @Override
//    public ApplicationDto update(ApplicationDto applicationDto) {
//        Optional<Application> applicationOpt = applicationRepository.findById(applicationDto.getId());
//        if(!applicationOpt.isPresent()){
//            System.out.println("Application not found!");
//            return null;
//        }
//        Application application = mapper.mapToApplication(applicationDto);
//        return mapper.mapToDto(application);
//    }
//}
