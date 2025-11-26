package com.example.transactions.repository;

import com.example.transactions.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByPassengerId(String passengerId);
    List<Transaction> findByDriverId(String driverId);
}
