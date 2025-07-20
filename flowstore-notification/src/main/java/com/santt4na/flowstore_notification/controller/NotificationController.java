package com.santt4na.flowstore_notification.controller;

import com.santt4na.flowstore_notification.dto.NotificationRequestDTO;
import com.santt4na.flowstore_notification.service.EmailService;
import com.santt4na.flowstore_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
	
	private final NotificationService notificationService;
	private final EmailService emailService;
	
	@PostMapping
	public ResponseEntity<Void> send(@RequestBody NotificationRequestDTO request) {
		notificationService.sendNotification(request);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/email")
	public ResponseEntity<Void> sendEmail(@RequestBody NotificationRequestDTO request) {
		emailService.sendEmail(request);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/email/template")
	public ResponseEntity<Void> sendHtmlEmail(@RequestBody NotificationRequestDTO request) {
		emailService.sendHtmlEmail(request, true);
		return ResponseEntity.ok().build();
	}
}