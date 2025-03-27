package com.example.paymentservice.service;

import com.example.paymentservice.kafka.KafkaProducerService;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public Payment processPayment(double amount) {
        Payment payment = new Payment();
        payment.setOrderId(UUID.randomUUID().toString());
        payment.setAmount(amount);
        payment.setStatus("SUCCESS");

        payment = paymentRepository.save(payment);

        kafkaProducerService.sendPaymentEvent("Payment successful for Order ID: " + payment.getOrderId());

        return payment;
    }
}
