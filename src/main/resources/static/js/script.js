// Example JavaScript function to show a confirmation message after vehicle registration 
function showSuccessMessage(message) {
    const successDiv = document.getElementById('success-message');
    successDiv.innerHTML = message;
    successDiv.style.display = 'block';  // Make the success message visible
    setTimeout(() => successDiv.style.display = 'none', 5000);  // Hide after 5 seconds
}

function showErrorMessage(message) {
    const errorDiv = document.getElementById('error-message');
    errorDiv.innerHTML = message;
    errorDiv.style.display = 'block';  // Make the error message visible
    setTimeout(() => errorDiv.style.display = 'none', 5000);  // Hide after 5 seconds
}

// Example for form validation
function validateForm() {
    const vin = document.getElementById("vin").value;
    const make = document.getElementById("make").value;
    const model = document.getElementById("model").value;
    const year = document.getElementById("year").value;

    if (!vin || !make || !model || !year) {
        showErrorMessage("All fields are required!");
        return false;  // Prevent form submission
    }
    return true;  // Allow form submission if valid
}

// Function to submit the form data via Fetch API
function submitVehicleRegistration(event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way

    if (validateForm()) {
        const vehicleData = {
            vin: document.getElementById("vin").value,
            make: document.getElementById("make").value,
            model: document.getElementById("model").value,
            year: parseInt(document.getElementById("year").value),
            latitude: parseFloat(document.getElementById("latitude").value || 0),
            longitude: parseFloat(document.getElementById("longitude").value || 0),
        };

        fetch('/vehicle/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',  // Send JSON data
            },
            body: JSON.stringify(vehicleData),  // Stringify the vehicle data
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                showSuccessMessage(data.message);
            } else {
                showErrorMessage(data.error);  // Show error message
            }
        })
        .catch(error => {
            console.error("Error:", error);  // Log any errors
            showErrorMessage("Error registering vehicle: " + error);
        });
    }
}
