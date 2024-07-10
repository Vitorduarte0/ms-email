package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.IEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServices {
    @Autowired
    IEmailRepository iEmailRepository;
    @Autowired
    JavaMailSender emailSend;

    public EmailModel sendEmail(EmailModel emailModel ) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            emailSend.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException mailException) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return iEmailRepository.save(emailModel);
        }

    }
}
