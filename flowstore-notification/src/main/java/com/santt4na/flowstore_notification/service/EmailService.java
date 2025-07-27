package com.santt4na.flowstore_notification.service;

import com.santt4na.flowstore_notification.dto.NotificationRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final JavaMailSender mailSender;
	private final ResourceLoader resourceLoader;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	public void sendEmail(NotificationRequestDTO dto) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			
			helper.setTo(dto.emailTarget());
			helper.setSubject(dto.title());
			helper.setText(dto.message(), false);
			helper.setFrom(emailFrom);
			
			mailSender.send(message);
			System.out.println("E-mail enviado com sucesso para: " + dto.emailTarget());
			
		} catch (MessagingException e) {
			throw new RuntimeException("Erro ao enviar e-mail", e);
		}
	}
	
	public void sendHtmlEmail(NotificationRequestDTO dto, Boolean html) {
		try {
			
			Resource resource = resourceLoader.getResource("classpath:templates/ConfirmacaoCompraTemplate.html");
			if (!resource.exists()) {
				throw new IllegalStateException("HTML Template Not Found in ClassPath: Templates/ConfirmaCompratemplate.html");
			}
			String htmlTemplate = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			
			String htmlContent = String.format(htmlTemplate,
				dto.userName()
			);
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setTo(dto.emailTarget());
			helper.setSubject(dto.title());
			helper.setFrom(emailFrom);
			helper.setText(htmlContent, html);
			
			mailSender.send(message);
			System.out.println("E-mail HTML enviado com sucesso para: " + dto.emailTarget());
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao enviar e-mail HTML: " + e.getMessage(), e);
		}
	}
}