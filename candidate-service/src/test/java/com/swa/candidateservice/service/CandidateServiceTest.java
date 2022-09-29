package com.swa.candidateservice.service;

import com.swa.candidateservice.entity.Candidate;
import com.swa.candidateservice.mapper.Mapper;
import com.swa.candidateservice.repository.CandidateRepository;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.EmailDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    private Candidate newCandidate;

    @Mock
    private Mapper mapper;

    @Mock
    private KafkaTemplate<String, CandidateDTO> kafkaTemplate;

    @Mock
    private KafkaTemplate<String, EmailDto> kafkaEmailTemplate;

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    public void beforeEach() {
        newCandidate= createCandidate();
        mapper = new Mapper();
    }

    private Candidate createCandidate() {
        Candidate candidate = new Candidate();
        candidate.setEmail("testmailswa@gmail.com");
        candidate.setCandidateID("1234");
        candidate.setAddress("1000N 4Th ST Fairfield, IOWA");
        candidate.setSkillDesc("Java, Angular");
        candidate.setFullName("Sanjaya Koju");
        candidate.setSummary("Full Stack Java Developer");
        return candidate;
    }

    @Test
    public void createCandidateProfile() {
        newCandidate.setCandidateID(null);
        when(candidateRepository.save(newCandidate)).thenReturn(newCandidate);
        CandidateDTO candidateDTO = mapper.mappedToCandidateDTO(newCandidate);
        Assertions.assertEquals(candidateService.registerCandidate(candidateDTO), candidateDTO);
    }

    @Test
    public void findCandidateById() {
        Optional<Candidate> candidate = Optional.ofNullable(newCandidate);
        Optional<Candidate> candidate1 = candidateRepository.findById(newCandidate.getCandidateID());
        when(candidate1).thenReturn(candidate);
        CandidateDTO candidateDTO = mapper.mappedToCandidateDTO(candidate.get());
        Assertions.assertEquals(candidateService.findById(newCandidate.getCandidateID()), candidateDTO);
    }

    @Test
    public void findAllCandidate() {
        List<Candidate> candidateList = List.of(createCandidate(),
                new Candidate("2121", "Shrawan Adhikari", "abc@gmail.com", "Senior Software Engineer", "Java, Angular, React", "100N 4th ST Fairfield, IOWA"));
        List<CandidateDTO> candidateDTOList = new ArrayList<>();
        when(candidateRepository.findAll()).thenReturn(candidateList);
        for (Candidate candidate: candidateList) {
            CandidateDTO candidateDTO = mapper.mappedToCandidateDTO(candidate);
            candidateDTOList.add(candidateDTO);
        }
        Assertions.assertEquals(candidateService.findAll(), candidateDTOList);
    }
}
