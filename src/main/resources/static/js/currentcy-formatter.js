document.addEventListener('DOMContentLoaded', function() {
    const currencyInputs = document.querySelectorAll('[data-format="currency"]');

    currencyInputs.forEach(input => {
        // Validate on input - this will show error if comma is typed
        input.addEventListener('input', function() {
            if (window.loanFormValidation) {
                window.loanFormValidation.validateField(this);
            }
        });

        // Validate on blur
        input.addEventListener('blur', function() {
            if (window.loanFormValidation) {
                window.loanFormValidation.validateField(this);
            }
        });
    });

    // Remove commas before form submission
    const form = document.getElementById('loanAssessmentForm');
    if (form) {
        form.addEventListener('submit', function(e) {
            currencyInputs.forEach(input => {
                input.value = input.value.replace(/,/g, '');
            });
        }, true);
    }
});
