package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.VehicleModel; // Change this import to VehicleModel

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, String> {
    // No additional methods are required, basic CRUD operations are provided by JpaRepository
}


