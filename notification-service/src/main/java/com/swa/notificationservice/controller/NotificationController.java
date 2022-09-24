package com.swa.notificationservice.controller;

import com.swa.notificationservice.dto.CandidateEmailDto;
import com.swa.notificationservice.dto.RecruiterEmailDto;
import com.swa.notificationservice.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping("/candidate/send-email")
    public ResponseEntity<?> sendCandidateNotification(@RequestBody CandidateEmailDto candidateEmailDto) {
        emailSenderService.sendMail(candidateEmailDto.getEmail(), candidateEmailDto.getSubject(), candidateEmailDto.getMessage());
        return new ResponseEntity<>("Mail Send Successfully...",HttpStatus.OK);
    }

    @PostMapping("/recruiter/send-email")
    public ResponseEntity<?> sendRecruiterNotification(@RequestBody RecruiterEmailDto recruiterEmailDto) {
        emailSenderService.sendMail(recruiterEmailDto.getEmail(), recruiterEmailDto.getSubject(), recruiterEmailDto.getMessage());
        return new ResponseEntity<>("Mail Send Successfully...",HttpStatus.OK);
    }
}
