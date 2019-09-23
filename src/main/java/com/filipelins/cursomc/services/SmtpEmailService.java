package com.filipelins.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Enviando e-mail...");
		mailSender.send(msg);
		log.info("E-mail enviado");
	}

}
