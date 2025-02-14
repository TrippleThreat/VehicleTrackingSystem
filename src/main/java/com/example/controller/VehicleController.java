package com.example.controller;

import com.example.model.VehicleModel;
import com.example.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // POST method for vehicle registration with error handling
    @PostMapping("/register")
    public ResponseEntity<String> registerVehicle(@RequestBody @Valid VehicleModel vehicleModel) {
        // Check if the vehicle already exists (based on VIN)
        if (vehicleRepository.existsById(vehicleModel.getVin())) {
            return new ResponseEntity<>("Vehicle with VIN " + vehicleModel.getVin() + " already exists", HttpStatus.BAD_REQUEST);
        }

        // Check if any required fields are missing (optional, based on your requirements)
        if (vehicleModel.getVin() == null || vehicleModel.getVin().isEmpty()) {
            return new ResponseEntity<>("VIN is required", HttpStatus.BAD_REQUEST);
        }
        if (vehicleModel.getMake() == null || vehicleModel.getMake().isEmpty()) {
            return new ResponseEntity<>("Make is required", HttpStatus.BAD_REQUEST);
        }
        if (vehicleModel.getModel() == null || vehicleModel.getModel().isEmpty()) {
            return new ResponseEntity<>("Model is required", HttpStatus.BAD_REQUEST);
        }
        if (vehicleModel.getYear() <= 0) {
            return new ResponseEntity<>("Year is invalid", HttpStatus.BAD_REQUEST);
        }

        try {
            // Save the vehicle to the database
            vehicleRepository.save(vehicleModel);
            return new ResponseEntity<>("Vehicle registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error and return an internal server error response
            return new ResponseEntity<>("Error registering vehicle: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET method to retrieve all vehicles
    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles() {
        try {
            var vehicles = vehicleRepository.findAll();
            if (vehicles.isEmpty()) {
                return new ResponseEntity<>("No vehicles found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            // Handle any errors that occur while fetching the vehicles
            return new ResponseEntity<>("Error retrieving vehicles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
