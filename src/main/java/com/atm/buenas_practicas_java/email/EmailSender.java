package com.atm.buenas_practicas_java.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailSender {

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public EmailSender(MailSender mailSender, SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
}
