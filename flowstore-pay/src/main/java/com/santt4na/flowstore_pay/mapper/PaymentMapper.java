package com.santt4na.flowstore_pay.mapper;

import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
	
	PaymentRequestDTO toDto(Payment payment);
	PaymentResponseDTO toResponseDto(Payment payment);
	
	Payment toEntity(PaymentRequestDTO dto);
}