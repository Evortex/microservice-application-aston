package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        kafkaTemplate.send("order-created", "Order created: " + savedOrder.getId());

        return savedOrder;
    }
}
