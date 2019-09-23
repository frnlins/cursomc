package com.filipelins.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.filipelins.cursomc.services.DBService;
import com.filipelins.cursomc.services.EmailService;
import com.filipelins.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public void instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
