package com.santt4na.flowstore_notification.service;

import com.santt4na.flowstore_notification.dto.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	public void sendNotification(NotificationRequestDTO request) {
		
		System.out.println("== Enviando Notificação ==");
		System.out.println("Para: " + request.emailTarget());
		System.out.println("Assunto: " + request.title());
		System.out.println("Mensagem: " + request.message());
	}
}