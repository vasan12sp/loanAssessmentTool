package com.msme.util;

public class CurrencyParser {

    /**
     * Removes commas, dots, spaces and other non-digit characters
     * Handles inputs like: "12,00,000", "100.50", "1,00,000.00"
     * Returns clean numeric string
     */
    public static String cleanCurrency(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "0";
        }

        // Remove all non-digit characters (commas, dots, spaces, etc.)
        String cleaned = value.replaceAll("[^\\d]", "");

        // Remove leading zeros
        cleaned = cleaned.replaceFirst("^0+(?!$)", "");

        return cleaned.isEmpty() ? "0" : cleaned;
    }

    /**
     * Parse currency string to Double
     */
    public static Double parseCurrency(String value) {
        String cleaned = cleanCurrency(value);
        try {
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
