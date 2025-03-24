package com.example.ticketservice.controller;

import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.bookTicket(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long orderId) {
        ticketService.cancelTicket(orderId);
        return ResponseEntity.noContent().build();
    }
}
