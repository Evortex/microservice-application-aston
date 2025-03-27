package com.example.paymentservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "order-events", groupId = "payment-group")
    public void processOrderEvent(String orderEvent) {
        System.out.println("Received Order Event: " + orderEvent);
    }
}
