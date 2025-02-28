package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.TransactionModel;  // Correct import for the model class
import com.example.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Save a new transaction
    public TransactionModel saveTransaction(TransactionModel transaction) {
        return transactionRepository.save(transaction);
    }

    // Get all transactions for a specific vehicle by VIN
    public List<TransactionModel> getTransactionsByVehicle(String vin) {
        // Changed the method to use findByVehicleVin
        return transactionRepository.findByVehicleVin(vin);
    }

    // Get all transactions of a specific type
    public List<TransactionModel> getTransactionsByType(String transactionType) {
        return transactionRepository.findByTransactionType(transactionType);
    }

    // Get all transactions in the system
    public List<TransactionModel> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
