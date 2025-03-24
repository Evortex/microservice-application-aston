package com.example.paymentservice.controller;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.processPayment(payment);
        return ResponseEntity.ok(savedPayment);
    }
}
