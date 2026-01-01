package com.example.transactions.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.transactions.status.TransactionStatus;

import java.time.Instant;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String orderId;
    private String passengerId;
    private String driverId;
    private double amount;
    private String currency = "EUR";
    private Instant createdAt;
    private TransactionStatus status; // PENDING, COMPLETED, REFUNDED
    private PaymentMethod paymentMethod;

    public Transaction() {}

    public Transaction(String orderId, String passengerId, String driverId, double amount, TransactionStatus status, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.createdAt = Instant.now();
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

}
