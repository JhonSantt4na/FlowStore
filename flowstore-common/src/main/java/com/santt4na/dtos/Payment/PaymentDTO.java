package com.santt4na.dtos.Payment;

import com.santt4na.enums.OrderStatus;
import com.santt4na.enums.PaymentMethod;

public record PaymentDTO(Long id, String OrderId, double amount, OrderStatus status, PaymentMethod payMethod) {
}
