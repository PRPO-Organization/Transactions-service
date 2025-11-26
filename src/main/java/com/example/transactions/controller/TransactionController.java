package com.example.transactions.controller;

import com.example.transactions.dto.FareRequest;
import com.example.transactions.model.Transaction;
import com.example.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    @Value("${fare.base:1.0}")
    private double fareBase;
    @Value("${fare.per_km:0.8}")
    private double farePerKm;
    @Value("${fare.per_min:0.2}")
    private double farePerMin;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/fare")
    public ResponseEntity<Double> calculateFare(@Validated @RequestBody FareRequest req) {
        double price = (fareBase + farePerKm * req.getDistanceKm() + farePerMin * req.getDurationMin()) * req.getSurge();
        return ResponseEntity.ok(Math.round(price * 100.0) / 100.0);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction t) {
        if (t.getStatus() == null) t.setStatus("PENDING");
        Transaction saved = service.create(t);
        return ResponseEntity.created(URI.create("/api/transactions/"+saved.getId())).body(saved);
    }

    @GetMapping
    public List<Transaction> list() {
        /////////////////////////
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable String id) {
        return service.get(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<Transaction> refund(@PathVariable String id) {
        try {
            Transaction r = service.refund(id);
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
