package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class VehicleModel {

    @Id
    @NotBlank(message = "VIN is required")  // Ensures VIN is not blank
    @Size(min = 17, max = 17, message = "VIN must be exactly 17 characters") // VIN length check (typically 17 characters)
    private String vin;  // Unique identifier for the vehicle (Vehicle Identification Number)

    @NotBlank(message = "Make is required")  // Ensures Make is not blank
    private String make;

    @NotBlank(message = "Model is required")  // Ensures Model is not blank
    private String model;

    @NotNull(message = "Year is required")  // Ensures Year is not null
    @Positive(message = "Year must be a positive number")  // Ensures Year is a positive number
    private int year;

    // Getters and setters
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
