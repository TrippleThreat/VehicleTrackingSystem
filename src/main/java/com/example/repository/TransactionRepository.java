package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    
    // Find all transactions related to a specific vehicle using vin
    List<TransactionModel> findByVehicleVin(String vin);

    // Optionally, add other methods for filtering by transaction type, amount, etc.
    List<TransactionModel> findByTransactionType(String transactionType);
}


