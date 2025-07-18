package com.santt4na.flowstore_pay.repository;

import com.santt4na.flowstore_pay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
	Optional<Payment> findByOrderId(String orderId);
}