document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loanAssessmentForm');
    const inputs = form.querySelectorAll('input[type="text"]');
    const selects = form.querySelectorAll('select');

    // Set up validation for text inputs
    inputs.forEach(input => {
        // Clear default 0.0 values
        if (input.value === '0.0' || input.value === '0') {
            input.value = '';
        }

        // Add input event listener for real-time validation
        input.addEventListener('input', function() {
            validateField(this);
        });

        // Add blur event listener for final validation
        input.addEventListener('blur', function() {
            validateField(this);
        });

        // Add focus event listener to clear errors
        input.addEventListener('focus', function() {
            this.classList.remove('is-invalid', 'is-valid');
        });
    });

    // Set up validation for select dropdowns
    selects.forEach(select => {
        select.addEventListener('change', function() {
            validateSelect(this);
        });

        select.addEventListener('blur', function() {
            validateSelect(this);
        });
    });

    // Form submission validation
    form.addEventListener('submit', function(e) {
        let isValid = true;

        inputs.forEach(input => {
            if (!validateField(input)) {
                isValid = false;
            }
        });

        selects.forEach(select => {
            if (!validateSelect(select)) {
                isValid = false;
            }
        });

        if (!isValid) {
            e.preventDefault();
            alert('Please fill all mandatory fields correctly before submitting.');
        }
    });

    function validateField(input) {
        const errorDiv = input.parentElement.querySelector('.error-message');
        const validationType = input.getAttribute('data-validation');
        const minValue = parseFloat(input.getAttribute('data-min'));
        const maxValue = parseFloat(input.getAttribute('data-max'));
        const isRequired = input.hasAttribute('required');
        const value = input.value.trim();

        // Clear previous error
        errorDiv.textContent = '';
        input.classList.remove('is-invalid', 'is-valid');

        // Check if field is empty
        if (value === '') {
            if (isRequired) {
                showError(input, errorDiv, 'This field is required');
                return false;
            }
            return true;
        }

        // Check if value is a valid number
        if (isNaN(value)) {
            showError(input, errorDiv, 'Please enter a valid number');
            return false;
        }

        const numValue = parseFloat(value);

        // Validation based on type
        switch(validationType) {
            case 'positive':
                if (numValue < 0) {
                    showError(input, errorDiv, 'Value cannot be negative');
                    return false;
                }
                if (!isNaN(minValue) && numValue < minValue) {
                    showError(input, errorDiv, `Value must be at least ${minValue}`);
                    return false;
                }
                if (!isNaN(maxValue) && numValue > maxValue) {
                    showError(input, errorDiv, `Value cannot exceed ${maxValue}`);
                    return false;
                }
                break;

            case 'range':
                if (!isNaN(minValue) && numValue < minValue) {
                    showError(input, errorDiv, `Value must be between ${minValue} and ${maxValue}`);
                    return false;
                }
                if (!isNaN(maxValue) && numValue > maxValue) {
                    showError(input, errorDiv, `Value must be between ${minValue} and ${maxValue}`);
                    return false;
                }
                break;

            case 'number':
                // Allow negative numbers for fields like Net Worth, Net Operating Income
                if (!isNaN(minValue) && numValue < minValue) {
                    showError(input, errorDiv, `Value must be at least ${minValue}`);
                    return false;
                }
                if (!isNaN(maxValue) && numValue > maxValue) {
                    showError(input, errorDiv, `Value cannot exceed ${maxValue}`);
                    return false;
                }
                break;
        }

        // Validation passed
        input.classList.add('is-valid');
        return true;
    }

    function validateSelect(select) {
        const errorDiv = select.parentElement.querySelector('.error-message');
        const isRequired = select.hasAttribute('required');
        const value = select.value;

        errorDiv.textContent = '';
        select.classList.remove('is-invalid', 'is-valid');

        if (value === '' && isRequired) {
            showError(select, errorDiv, 'Please select an option');
            return false;
        }

        if (value !== '') {
            select.classList.add('is-valid');
        }
        return true;
    }

    function showError(element, errorDiv, message) {
        element.classList.add('is-invalid');
        errorDiv.textContent = message;
    }
});
