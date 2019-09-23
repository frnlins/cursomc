package com.filipelins.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.filipelins.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);
}
