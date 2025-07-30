package com.santt4na.flowstore_notification.controller;

import com.santt4na.flowstore_notification.dto.NotificationRequestDTO;
import com.santt4na.flowstore_notification.service.EmailService;
import com.santt4na.flowstore_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
	
	private final NotificationService notificationService;
	private final EmailService emailService;
	
	@PostMapping
	public ResponseEntity<Void> send(@RequestBody NotificationRequestDTO request) {
		log.info("Sending notification: Type=TEXT, Recipient={}", request.emailTarget());
		notificationService.sendNotification(request);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/email")
	public ResponseEntity<Void> sendEmail(@RequestBody NotificationRequestDTO request) {
		log.info("Sending plain email to: {}", request.emailTarget());
		emailService.sendEmail(request);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/email/template")
	public ResponseEntity<Void> sendHtmlEmail(@RequestBody NotificationRequestDTO request) {
		log.info("Sending HTML template email to: {}", request.emailTarget());
		emailService.sendHtmlEmail(request, true);
		return ResponseEntity.ok().build();
	}
}