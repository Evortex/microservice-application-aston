package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Payment processPayment(Payment payment) {
        payment.setStatus("SUCCESS");
        Payment savedPayment = paymentRepository.save(payment);

        // Отправка события об успешном платеже
        kafkaTemplate.send("payment-processed", "Payment successful for order: " + savedPayment.getOrderId());

        return savedPayment;
    }

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void handleOrderCreated(String message) {
        System.out.println("Received message from Kafka: " + message);
        // Здесь может быть логика автоматического списания средств
    }
}
