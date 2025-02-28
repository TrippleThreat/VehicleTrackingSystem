package com.example.service;

import com.example.model.VehicleLocation;
import com.example.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTrackingService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Send vehicle updates every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void sendVehicleUpdates() {
        List<VehicleLocation> vehicles = vehicleRepository.findAll();
        // Send vehicle data to all connected clients on /topic/vehicles
        messagingTemplate.convertAndSend("/topic/vehicles", vehicles);
    }
}
