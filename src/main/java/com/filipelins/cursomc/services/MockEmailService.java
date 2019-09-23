package com.filipelins.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockEmailService extends AbstractEmailService {

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Simulando envio de e-mail...");
		log.info(msg.toString());
		log.info("E-mail enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Simulando envio de e-mail html...");
		log.info(msg.toString());
		log.info("E-mail enviado");
	}

}
