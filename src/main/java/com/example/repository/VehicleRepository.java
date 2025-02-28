package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.VehicleLocation;  // Import VehicleLocation

@Repository
public interface VehicleRepository extends JpaRepository<VehicleLocation, String> {
    // No additional methods are required, basic CRUD operations are provided by JpaRepository
}
