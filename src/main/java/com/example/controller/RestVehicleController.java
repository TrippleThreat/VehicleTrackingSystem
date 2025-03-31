package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.VehicleTrackingService;
import com.example.model.VehicleLocation; // Ensure VehicleLocation is imported

@RestController
@RequestMapping("/api/vehicles")
public class RestVehicleController {

    // Inject the VehicleTrackingService using @Autowired
    @Autowired
    private VehicleTrackingService vehicleTrackingService;

    // Endpoint to get all vehicles
    @GetMapping("/all")
    public List<VehicleLocation> getAllVehicles() {
        // Call the method using the instance of VehicleTrackingService
        return vehicleTrackingService.getAllVehicles();
    }

    // Endpoint to get a specific vehicle by VIN
    @GetMapping("/{vin}")
    public VehicleLocation getVehicleByVin(@PathVariable String vin) {
        // Call the service method to fetch the vehicle by VIN
        return vehicleTrackingService.getVehicleByVin(vin);
    }

    // Endpoint to register a new vehicle
    @PostMapping("/register")
    public VehicleLocation registerVehicle(@RequestBody VehicleLocation vehicle) {
        // Assuming registerVehicle is a method in the service
        return vehicleTrackingService.registerVehicle(vehicle);
    }
}
