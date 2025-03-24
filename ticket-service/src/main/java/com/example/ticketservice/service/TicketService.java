package com.example.ticketservice.service;

import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Ticket bookTicket(Ticket ticket) {
        ticket.setStatus("BOOKED");
        Ticket savedTicket = ticketRepository.save(ticket);

        kafkaTemplate.send("ticket-booked", "Ticket booked for order: " + savedTicket.getOrderId());

        return savedTicket;
    }

    @Transactional
    public void cancelTicket(Long orderId) {
        Ticket ticket = ticketRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus("CANCELED");
        ticketRepository.save(ticket);

        kafkaTemplate.send("ticket-canceled", "Ticket canceled for order: " + orderId);
    }

    @KafkaListener(topics = "payment-processed", groupId = "ticket-group")
    public void handlePaymentProcessed(String message) {
        System.out.println("Received payment confirmation: " + message);
    }
}
