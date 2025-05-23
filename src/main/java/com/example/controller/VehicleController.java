package com.example.controller;

import com.example.model.VehicleLocation;
import com.example.model.VehicleModel;
import com.example.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // GET method to display the registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("vehicle", new VehicleModel());  // Add empty vehicle object for binding
        return "register";  // Render the registration form
    }

    // POST method for vehicle registration with location data
    @PostMapping("/register")
    public String registerVehicle(@ModelAttribute("vehicle") @Valid VehicleModel vehicleModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";  // Return to the form if there are validation errors
        }

        // Validate if VIN is present
        if (vehicleModel.getVin() == null || vehicleModel.getVin().isEmpty()) {
            model.addAttribute("error", "VIN is required");
            return "register";  // Return to the registration form with an error message
        }

        // Check if VIN already exists in the database
        if (vehicleRepository.existsById(vehicleModel.getVin())) {
            model.addAttribute("error", "Vehicle with VIN " + vehicleModel.getVin() + " already exists");
            return "register";  // Return to the registration form with an error message
        }

        // Create a VehicleLocation instance and populate it with location data
        VehicleLocation vehicleLocation = new VehicleLocation();
        vehicleLocation.setVin(vehicleModel.getVin());
        vehicleLocation.setMake(vehicleModel.getMake());
        vehicleLocation.setModel(vehicleModel.getModel());
        vehicleLocation.setYear(vehicleModel.getYear());

        // For now, set latitude and longitude to default values (to be updated with actual data)
        vehicleLocation.setLatitude(0.0);
        vehicleLocation.setLongitude(0.0);

        try {
            // Save the vehicle with location data to the database
            vehicleRepository.save(vehicleLocation);
            model.addAttribute("success", "Vehicle registered successfully!");
            return "redirect:/vehicle/all";  // Redirect to the page that lists all vehicles after successful registration
        } catch (Exception e) {
            model.addAttribute("error", "Error registering vehicle: " + e.getMessage());
            return "register";  // Return to the registration form with an error message
        }
    }

    // GET method to retrieve all vehicles and show the list on a page
    @GetMapping("/all")
    public String getAllVehicles(Model model) {
        try {
            List<VehicleLocation> vehicles = vehicleRepository.findAll();  // Retrieve all vehicle location data
            if (vehicles.isEmpty()) {
                model.addAttribute("message", "No vehicles found");
            } else {
                model.addAttribute("vehicles", vehicles);
            }
            return "vehicleList";  // Thymeleaf will search for a template named "vehicleList.html"
        } catch (Exception e) {
            model.addAttribute("error", "Error retrieving vehicles: " + e.getMessage());
            return "vehicleList";  // In case of error, show the vehicle list page
        }
    }

    // Method to periodically update vehicle locations (e.g., every 5 seconds)
    @Scheduled(fixedRate = 5000)
    public void sendVehicleLocations() {
        List<VehicleLocation> vehicles = vehicleRepository.findAll();  // Retrieve all vehicle location data
        messagingTemplate.convertAndSend("/topic/vehicles", vehicles);  // Send the vehicle data to connected clients
    }

    // NEW API ENDPOINT: Check if VIN is already registered
    @PostMapping("/api/check-vin")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkVin(@RequestBody Map<String, String> vinRequest) {
        String vin = vinRequest.get("vin");

        // Check if the VIN exists in the database
        boolean isRegistered = vehicleRepository.existsById(vin);

        // Return response
        Map<String, Boolean> response = new HashMap<>();
        response.put("isRegistered", isRegistered);

        return ResponseEntity.ok(response);  // Return response with status 200 OK
    }

    // Update vehicle information based on VIN
    @GetMapping("/update/{vin}")
    public String showUpdateForm(@PathVariable("vin") String vin, Model model) {
        VehicleLocation vehicle = vehicleRepository.findById(vin).orElse(null);
        if (vehicle == null) {
            model.addAttribute("error", "Vehicle with VIN " + vin + " not found");
            return "vehicleList";  // Redirect to the list page if the vehicle is not found
        }

        model.addAttribute("vehicle", vehicle);  // Populate the form with existing vehicle data
        return "updateVehicle";  // Render the update form
    }

    @PostMapping("/update")
    public String updateVehicle(@ModelAttribute("vehicle") VehicleLocation vehicleLocation, Model model) {
        try {
            VehicleLocation existingVehicle = vehicleRepository.findById(vehicleLocation.getVin()).orElse(null);
            if (existingVehicle == null) {
                model.addAttribute("error", "Vehicle with VIN " + vehicleLocation.getVin() + " not found");
                return "vehicleList";  // Return to the vehicle list page if the vehicle doesn't exist
            }

            // Update vehicle data
            existingVehicle.setMake(vehicleLocation.getMake());
            existingVehicle.setModel(vehicleLocation.getModel());
            existingVehicle.setYear(vehicleLocation.getYear());
            existingVehicle.setLatitude(vehicleLocation.getLatitude());
            existingVehicle.setLongitude(vehicleLocation.getLongitude());

            vehicleRepository.save(existingVehicle);
            model.addAttribute("success", "Vehicle updated successfully!");
            return "redirect:/vehicle/all";  // Redirect to the list page after successful update
        } catch (Exception e) {
            model.addAttribute("error", "Error updating vehicle: " + e.getMessage());
            return "updateVehicle";  // Return to the update form if an error occurs
        }
    }

    // Delete a vehicle by VIN
    @PostMapping("/delete/{vin}")
    public String deleteVehicle(@PathVariable("vin") String vin, Model model) {
        try {
            vehicleRepository.deleteById(vin);
            model.addAttribute("success", "Vehicle with VIN " + vin + " deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting vehicle: " + e.getMessage());
        }
        return "redirect:/vehicle/all";  // Redirect to the list page after deletion
    }
}
