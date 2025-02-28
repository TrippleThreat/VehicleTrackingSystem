package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TransactionService;
import com.example.model.TransactionModel;  // Correct import for the model class

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to create a new transaction
    @PostMapping
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionModel transaction) {
        TransactionModel savedTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    // Endpoint to get all transactions for a specific vehicle by VIN
    @GetMapping("/vehicle/{vin}")
    public ResponseEntity<List<TransactionModel>> getTransactionsByVehicle(@PathVariable String vin) {
        List<TransactionModel> transactions = transactionService.getTransactionsByVehicle(vin);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Endpoint to get transactions by type (e.g., PURCHASE, MAINTENANCE)
    @GetMapping("/type/{transactionType}")
    public ResponseEntity<List<TransactionModel>> getTransactionsByType(@PathVariable String transactionType) {
        List<TransactionModel> transactions = transactionService.getTransactionsByType(transactionType);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Endpoint to get all transactions in the system
    @GetMapping
    public ResponseEntity<List<TransactionModel>> getAllTransactions() {
        List<TransactionModel> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
