package com.example.transactions.service;

import com.example.transactions.model.PaymentMethod;
import com.example.transactions.model.Transaction;
import com.example.transactions.repository.TransactionRepository;
import com.example.transactions.status.TransactionStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction create(Transaction t) {
        if (t.getCreatedAt() == null) 
        {
            t.setCreatedAt(Instant.now());
        }
        if (t.getStatus() == null) 
        {
            t.setStatus(TransactionStatus.PENDING);
        }
        if (t.getPaymentMethod() == null)
        {
            t.setPaymentMethod(PaymentMethod.CARD);
        }
        return repo.save(t);
    }

    public Optional<Transaction> get(String id) {
        return repo.findById(id);
    }

    public List<Transaction> list() {
        return repo.findAll();
    }

    public List<Transaction> listByPassenger(String passengerId) {
        return repo.findByPassengerId(passengerId);
    }

    public List<Transaction> listByDriver(String driverId) {
        return repo.findByDriverId(driverId);
    }


    public Transaction refund(String id) {
        Optional<Transaction> ot = repo.findById(id);
        if (ot.isEmpty()) throw new IllegalArgumentException("Transaction not found");
        Transaction t = ot.get();
        if (t.getStatus() == TransactionStatus.REFUNDED) 
        {
            throw new IllegalStateException("Transaction already refunded");
        }
        t.setStatus(TransactionStatus.REFUNDED);
        return repo.save(t);
    }

    public Optional<Transaction> complete(String id) {
        Optional<Transaction> ot = repo.findById(id);
        if (ot.isEmpty()) return Optional.empty();
        Transaction t = ot.get();
        t.setStatus(TransactionStatus.COMPLETED);
        return Optional.of(repo.save(t));
    }

}
