package com.example.transactions.service;

import com.example.transactions.model.Transaction;
import com.example.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction create(Transaction t) {
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

    public Transaction refund(String id) {
        Optional<Transaction> ot = repo.findById(id);
        if (ot.isEmpty()) throw new IllegalArgumentException("Transaction not found");
        Transaction t = ot.get();
        t.setStatus("REFUNDED");
        return repo.save(t);
    }
}
