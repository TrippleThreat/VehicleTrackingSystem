package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.VehicleLocation;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleLocation, String> {

    // Method to find all vehicle locations
    List<VehicleLocation> findAll();

    // Custom query method to find a vehicle by its unique identifier (VIN)
    VehicleLocation findByVin(String vin);  // Changed to 'vin' instead of 'vehicleId'

    // You can add more custom query methods as needed (for example, by location, model, etc.)
}
