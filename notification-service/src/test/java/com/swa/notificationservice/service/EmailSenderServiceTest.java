package com.swa.notificationservice.service;

import com.swa.proj3commonmodule.dto.EmailDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailSenderServiceTest {

    private EmailDto newEmailDto;

    @Mock
    private EmailSenderService emailSenderService;

    @BeforeEach
    public void beforeEach() {
        newEmailDto = createEmailDto();
    }

    private EmailDto createEmailDto() {
        EmailDto emailDto = new EmailDto();
        emailDto.setMessage("Hello World!");
        emailDto.setEmail("testmailswa@gmail.com");
        emailDto.setSubject("Test Mail");
        return emailDto;
    }

    @Test
    public void sendMail() {
        emailSenderService.sendMail(newEmailDto);
        Assertions.assertEquals(newEmailDto, new EmailDto("testmailswa@gmail.com", "Test Mail", "Hello World!"));
    }
}
