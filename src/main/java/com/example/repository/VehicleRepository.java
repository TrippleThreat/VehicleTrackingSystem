package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.VehicleLocation;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleLocation, String> {
    // Additional custom queries can go here if needed
}

