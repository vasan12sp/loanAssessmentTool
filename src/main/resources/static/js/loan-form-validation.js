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

        // Only add validation listeners to NON-currency fields
        // Currency fields are handled by currency-formatter.js
        const isCurrency = input.getAttribute('data-format') === 'currency';

        if (!isCurrency) {
            // Validate on input for immediate feedback
            input.addEventListener('input', function() {
                validateField(this);
            });
        }

        // Add blur event listener for final validation
        input.addEventListener('blur', function() {
            validateField(this);
        });

        // Add focus event listener to clear errors
        input.addEventListener('focus', function() {
            const errorDiv = this.parentElement.querySelector('.error-message') ||
                this.closest('.field-wrapper')?.parentElement.querySelector('.error-message');
            if (errorDiv) {
                errorDiv.textContent = '';
            }
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

    // Form submission validation - PREVENT SUBMISSION IF INVALID
    form.addEventListener('submit', function(e) {
        e.preventDefault(); // Always prevent default first

        let isValid = true;
        let firstInvalidField = null;

        // Validate all inputs
        inputs.forEach(input => {
            if (!validateField(input)) {
                isValid = false;
                if (!firstInvalidField) {
                    firstInvalidField = input;
                }
            }
        });

        // Validate all selects
        selects.forEach(select => {
            if (!validateSelect(select)) {
                isValid = false;
                if (!firstInvalidField) {
                    firstInvalidField = select;
                }
            }
        });

        if (!isValid) {
            // Scroll to first invalid field
            if (firstInvalidField) {
                firstInvalidField.scrollIntoView({ behavior: 'smooth', block: 'center' });
                firstInvalidField.focus();
            }

            // Show alert
            alert('Please correct all errors before submitting the form.');
            return false;
        }

        // If valid, submit the form
        form.submit();
    });

    function validateField(input) {
        const errorDiv = input.parentElement.querySelector('.error-message') ||
            input.closest('.field-wrapper')?.parentElement.querySelector('.error-message');
        const validationType = input.getAttribute('data-validation');
        const minValue = parseFloat(input.getAttribute('data-min'));
        const maxValue = parseFloat(input.getAttribute('data-max'));
        const isRequired = input.hasAttribute('required');
        const isCurrency = input.getAttribute('data-format') === 'currency';

        let value = input.value.trim();

        // Clear previous error
        if (errorDiv) {
            errorDiv.textContent = '';
        }
        input.classList.remove('is-invalid', 'is-valid');

        // Check if field is empty
        if (value === '') {
            if (isRequired) {
                showError(input, errorDiv, 'This field is required');
                return false;
            }
            input.classList.add('is-valid');
            return true;
        }

        // For currency fields: Check if commas are present (treat as invalid)
        if (isCurrency && value.includes(',')) {
            showError(input, errorDiv, 'Commas are not allowed. Please enter numbers only');
            return false;
        }

        // Check if value contains only valid characters (digits, decimal point, minus sign)
        if (!/^-?\d*\.?\d*$/.test(value)) {
            showError(input, errorDiv, 'Please enter only numeric values');
            return false;
        }

        // Check if value is a valid number
        if (value === '-' || value === '.' || value === '-.') {
            showError(input, errorDiv, 'Please enter a complete number');
            return false;
        }

        if (isNaN(value) || value === '') {
            showError(input, errorDiv, 'Please enter a valid number');
            return false;
        }

        const numValue = parseFloat(value);

        // Check for invalid number
        if (!isFinite(numValue)) {
            showError(input, errorDiv, 'Please enter a valid number');
            return false;
        }

        // Validation based on type
        switch(validationType) {
            case 'positive':
                if (numValue < 0) {
                    showError(input, errorDiv, 'Value cannot be negative');
                    return false;
                }
                if (numValue === 0) {
                    showError(input, errorDiv, 'Value must be greater than 0');
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
                if (!isNaN(minValue) && numValue < minValue) {
                    showError(input, errorDiv, `Value must be at least ${minValue}`);
                    return false;
                }
                if (!isNaN(maxValue) && numValue > maxValue) {
                    showError(input, errorDiv, `Value cannot exceed ${maxValue}`);
                    return false;
                }
                break;

            default:
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

        if (errorDiv) {
            errorDiv.textContent = '';
        }
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
        if (errorDiv) {
            errorDiv.textContent = message;
        }
    }

    // Expose validateField for currency-formatter to use
    window.loanFormValidation = { validateField };
});
