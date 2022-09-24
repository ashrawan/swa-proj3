package com.swa.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toMail, String subject, String body) {
        try {
            log.info("Mail Sending...");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("sanjayakoju42@gmail.com");
            message.setTo(toMail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
            log.info("Mail Send Successfully...");
        } catch (MailException e) {
            System.out.println("Send Simple MessageException : "+e);
        }

    }
}
