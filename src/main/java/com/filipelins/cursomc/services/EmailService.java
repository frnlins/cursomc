package com.filipelins.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.filipelins.cursomc.domain.Cliente;
import com.filipelins.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido pedido);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
