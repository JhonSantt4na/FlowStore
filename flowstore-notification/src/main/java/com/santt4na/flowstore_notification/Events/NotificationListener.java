package com.santt4na.flowstore_notification.Events;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_notification.dto.NotificationRequestDTO;
import com.santt4na.flowstore_notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

	@Autowired
	private EmailService emailService;
	
	@KafkaListener(topics = "payment-success", groupId = "notification-group")
	public void handlePaymentSuccess(OrderDTO dto) {
		NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(
			"santtanaj86@gmail.com",
			"Pagamento confirmado",
			"Seu Pedido " + dto.id() + " foi aprovado!",
			"Cliente"
		);
		emailService.sendEmail(notificationRequestDTO);
	}
	
}
