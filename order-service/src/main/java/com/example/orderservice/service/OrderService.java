package com.example.orderservice.service;

import com.example.orderservice.kafka.KafkaProducerService;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public Order createOrder(Order order) {
        order.setStatus("NEW");
        Order savedOrder = orderRepository.save(order);

        kafkaProducerService.sendMessage("order-topic", "Order created: " + savedOrder.getId());

        return savedOrder;
    }
}
