package com.swa.jobservice.service;

import com.swa.jobservice.entity.Job;
import com.swa.jobservice.repository.JobRepository;
import com.swa.proj3commonmodule.dto.EmailDto;
import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.proj3commonmodule.dto.JobRequest;
import com.swa.proj3commonmodule.response.ApplicationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;


    @Value("${spring.kafka.custom.job_topic}")
    private String jobTopic;

    @Value("${spring.kafka.custom.notification-topic}")
    private String notificationTopic;

    @Autowired
    private KafkaTemplate<String, EmailDto> kafkaEmailTemplate;

    @Autowired
    private KafkaTemplate<String, ApplicationResponse> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, JobDTO> kafkaJobTemplate;

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = Job.builder()
                .title(jobDTO.getTitle())
                .description(jobDTO.getDescription())
                .position(jobDTO.getPosition())
                .category(jobDTO.getCategory())
                .skills(jobDTO.getSkills())
                .build();
        Job saveJob = jobRepository.save(job);
        jobDTO.setJobId(saveJob.getJobId());
        kafkaJobTemplate.send(jobTopic, jobDTO);
        log.info("Job Created Successfully!!");

        EmailDto emailDto = new EmailDto();
        emailDto.setEmail("testmailswa@gmail.com");
        emailDto.setSubject("New Job Created");
        emailDto.setMessage("Hello \n New Job is created.");
        log.info("Producing Email Object : {} ",emailDto);
        kafkaEmailTemplate.send(notificationTopic, emailDto);
        return jobDTO;
    }

    @Override
    public JobDTO findById(String id) {
        Job job = jobRepository.findById(id).get();
        if(job == null) {
            // log.error("Job with id {} not found!!",id);
            throw new RuntimeException("Job Not Found!!");
        }
        JobDTO jobDTO = JobDTO.builder()
                .jobId(job.getJobId())
                .title(job.getTitle())
                .description(job.getDescription())
                .position(job.getPosition())
                .category(job.getCategory())
                .skills(job.getSkills())
                .build();
        return jobDTO;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobList = jobRepository.findAll();
        if(jobList == null) {
            throw new RuntimeException("Jobs is empty!!");
        }
        List<JobDTO> jobDTOList = jobList.stream()
                .map(job -> {
                    JobDTO jobDTO = new JobDTO();
                    jobDTO.setJobId(job.getJobId());
                    jobDTO.setTitle(job.getTitle());
                    jobDTO.setDescription(job.getDescription());
                    jobDTO.setCategory(job.getCategory());
                    jobDTO.setPosition(job.getPosition());
                    jobDTO.setSkills(job.getSkills());
                    return jobDTO;
                }).collect(Collectors.toList());
        return jobDTOList;
    }

    @Override
    public HttpStatus existJob(JobRequest dto) {
        log.info("----- checking job presence ");
        Optional<Job> jobOpt = jobRepository.findById(dto.getJobId());
        ApplicationResponse response = ApplicationResponse.builder()
                .jobId(dto.getJobId())
                .candidateId(dto.getCandidateId())
                .build();
        if(jobOpt.isPresent()){
            response.setHttpStatus(HttpStatus.FOUND);
        } else {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMessage("This job is not available.");
        }

        kafkaTemplate.send(jobTopic, response);
        return response.getHttpStatus();
    }
}
