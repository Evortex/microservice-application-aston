package com.example.ticketservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String eventName;
    private String status; // AVAILABLE, BOOKED, CANCELED
}
