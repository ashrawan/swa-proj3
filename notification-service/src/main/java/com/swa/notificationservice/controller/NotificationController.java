package com.swa.notificationservice.controller;

import com.swa.notificationservice.dto.CandidateEmailDto;
import com.swa.notificationservice.dto.RecruiterEmailDto;
import com.swa.notificationservice.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void sendCandidateNotification(@RequestBody CandidateEmailDto candidateEmailDto) {
        emailSenderService.sendMail(candidateEmailDto.getEmail(), candidateEmailDto.getSubject(), candidateEmailDto.getMessage());
    }

    @PostMapping("/recruiter/send-email")
    public void sendRecruiterNotification(@RequestBody RecruiterEmailDto recruiterEmailDto) {
        emailSenderService.sendMail(recruiterEmailDto.getEmail(), recruiterEmailDto.getSubject(), recruiterEmailDto.getMessage());
    }
}
