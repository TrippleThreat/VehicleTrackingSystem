package com.example.service;

import com.example.model.VehicleLocation; // VehicleLocation entity used for vehicle data
import com.example.repository.VehicleRepository; // Repository to interact with database
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate; // Used to send WebSocket messages
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTrackingService {

    @Autowired
    private VehicleRepository vehicleRepository; // Inject the repository to access vehicle data

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // Template to send messages to WebSocket clients

    // Method to get all vehicles from the database
    public List<VehicleLocation> getAllVehicles() {
        return vehicleRepository.findAll(); // Retrieve all vehicle location data from the repository
    }

    // Method to get a vehicle location by its VIN
    public VehicleLocation getVehicleByVin(String vin) {
        return vehicleRepository.findByVin(vin); // Retrieve a vehicle by its VIN from the repository
    }

    // Method to register a new vehicle location (Add a new vehicle to the database)
    public VehicleLocation registerVehicle(VehicleLocation vehicleLocation) {
        // Save the vehicle location to the repository
        return vehicleRepository.save(vehicleLocation); // Assuming the repository handles the save operation
    }

    // Send vehicle updates every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void sendVehicleUpdates() {
        List<VehicleLocation> vehicles = vehicleRepository.findAll(); // Get all vehicle locations
        // Send vehicle data to all connected clients on /topic/vehicles
        messagingTemplate.convertAndSend("/topic/vehicles", vehicles);
    }
}
