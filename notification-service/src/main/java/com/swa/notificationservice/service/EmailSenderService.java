package com.swa.notificationservice.service;

import com.swa.proj3commonmodule.dto.EmailDto;
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

    public Boolean sendMail(EmailDto emailDto) {
        Boolean emailSendFlag = false;
        try {
            log.info("Mail Sending...");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("sanjayakoju42@gmail.com");
            message.setTo(emailDto.getEmail());
            message.setText(emailDto.getMessage());
            message.setSubject(emailDto.getSubject());
            mailSender.send(message);
            log.info("Mail Send Successfully...");
            emailSendFlag = true;
        } catch (MailException e) {
            System.out.println("Send Simple MessageException : " + e);
        }
        System.out.println("Flag "+emailSendFlag);
        return emailSendFlag;
    }
}
