package com.swa.application.service.impl;

import com.swa.application.dto.ApplicationDto;
import com.swa.application.dto.mapper.ApplicationMapper;
import com.swa.application.model.Application;
import com.swa.application.repository.ApplicationRepository;
import com.swa.application.response.ServerResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {
    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    KafkaTemplate kafkaTemplate;

    @Mock
    ApplicationMapper applicationMapper;

    @InjectMocks
    ApplicationServiceImpl applicationService;


    ApplicationDto applicationDto ;
    Application application;

    @BeforeEach
    void setUp() {
        applicationDto = setApplicationDto();
        application = setApplication();
    }

    @Test
    public void testSave(){
        lenient().when(applicationRepository.save(any(Application.class))).thenReturn(application);
        ServerResponse serverResponse =  applicationService.save(applicationDto);
        assertEquals(201, serverResponse.getStatusCode());
    }


    @Test
    public void test_findAll(){
        List<Application> applications = getApplications();
        when(applicationRepository.findAll()).thenReturn(applications); //Mocking
        assertEquals(2,applicationService.findAll().size());
    }

    @Test
    public void findById(){
        applicationService.findById("500");
        verify(applicationRepository, times(1)).findById("500");
    }

    @Test
    public void update(){
        when(applicationRepository.findById(applicationDto.getId())).thenReturn(Optional.of(application));
        when(applicationMapper.mapToApplication(applicationDto)).thenReturn(application);
        when(applicationMapper.mapToDto(application)).thenReturn(applicationDto);
        Assertions.assertNotNull(applicationService.update(applicationDto));
    }

    public ApplicationDto setApplicationDto(){
        return ApplicationDto.builder()
                .resumeVersion(3.0f)
                .candidateId("500")
                .jobId("600")
                .date(LocalDate.of(2011,11,11))
                .build();
    }

    public Application setApplication(){
        return Application.builder()
                .resumeVersion(3.0f)
                .candidateId("500")
                .jobId("600")
                .date(LocalDate.of(2011,11,11))
                .build();
    }

    public List<Application> getApplications(){
        return Arrays.asList(
                Application.builder()
                        .id("100")
                        .resumeVersion(3.0f)
                        .candidateId("500")
                        .jobId("600")
                        .date(LocalDate.of(2010,01,10))
                        .build(),

                Application.builder()
                        .id("101")
                        .resumeVersion(3.01f)
                        .candidateId("501")
                        .jobId("601")
                        .date(LocalDate.of(2010,01,11))
                        .build()

        );
    }

    @Test
    public void testJunit_Cases(){
        String name = "Supriya";
        assertEquals("Supriya", name);
    }



}