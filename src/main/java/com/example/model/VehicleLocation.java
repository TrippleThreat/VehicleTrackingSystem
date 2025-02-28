package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class VehicleLocation {

    @Id
    @NotBlank(message = "VIN is required")
    private String vin;  // Unique identifier for the vehicle (Vehicle Identification Number)

    @NotBlank(message = "Make is required")
    private String make;  // Vehicle make

    @NotBlank(message = "Model is required")
    private String model;  // Vehicle model

    @NotNull(message = "Year is required")
    @Positive(message = "Year must be a positive number")
    private int year;  // Vehicle year

    @NotNull(message = "Latitude is required")
    private Double latitude;  // Latitude of the vehicle

    @NotNull(message = "Longitude is required")
    private Double longitude;  // Longitude of the vehicle

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
