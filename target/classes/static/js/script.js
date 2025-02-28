async function submitVehicleRegistration(event) {
    event.preventDefault(); // Prevent form submission

    const vin = document.getElementById('vin').value;
    const vinError = document.getElementById('vin-error');
    const vinDuplicateError = document.getElementById('vin-duplicate-error');
    const successMessage = document.getElementById('success-message');
    const errorMessage = document.getElementById('error-message');
    const vinLength = vin.length;

    // Hide any previous messages
    vinError.style.display = 'none';
    vinDuplicateError.style.display = 'none';
    successMessage.style.display = 'none';
    errorMessage.style.display = 'none';

    // Check if VIN is exactly 17 characters
    if (vinLength !== 17) {
        vinError.style.display = 'block'; // Show VIN length error
        return;
    }

    try {
        // Make the API request to check if the VIN is already registered
        const response = await fetch('http://localhost:8080/vehicle/api/check-vin', { // Full URL here
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ vin }) // Send VIN in the request body
        });

        if (!response.ok) {
            throw new Error('Failed to check VIN');
        }

        const data = await response.json();

        // Log response data for debugging
        console.log("VIN check response:", data);

        if (data.isRegistered) {
            vinDuplicateError.style.display = 'block'; // Show VIN already registered error
            successMessage.style.display = 'none';
        } else {
            successMessage.textContent = 'Vehicle Registered Successfully!';
            successMessage.style.display = 'block';
            vinDuplicateError.style.display = 'none'; // Hide duplicate error if VIN is unique
        }
    } catch (error) {
        console.error("Error checking VIN:", error);
        errorMessage.textContent = 'An error occurred while checking the VIN.';
        errorMessage.style.display = 'block';
    }
}
