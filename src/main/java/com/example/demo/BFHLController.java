package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class BFHLController {

    // POST endpoint
    @PostMapping
    public ResponseEntity<Map<String, Object>> handlePost(@RequestBody Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>();
        
        // User ID (formatted as full name and dob)
        response.put("user_id", "john_doe_17091999");  // Example user ID
        
        // Processing the input data
        List<String> alphabets = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        boolean primeFound = false;

        for (Object obj : (List<?>) input.get("data")) {
            if (obj instanceof String) {
                String value = (String) obj;
                if (value.matches("[a-zA-Z]")) {
                    alphabets.add(value);
                }
            } else if (obj instanceof Integer) {
                numbers.add((Integer) obj);
                // Check for prime number in the input
                if (isPrime((Integer) obj)) {
                    primeFound = true;
                }
            }
        }

        // Highest lowercase alphabet
        String highestLowercase = alphabets.stream()
            .filter(s -> s.matches("[a-z]"))
            .max(String::compareTo)
            .orElse("");

        // Build the response
        response.put("is_success", true);
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase", highestLowercase);
        response.put("prime_found", primeFound);

        return ResponseEntity.ok(response);
    }

    // GET endpoint
    @GetMapping
    public ResponseEntity<Map<String, Object>> handleGet() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code", 1);  // Static response

        return ResponseEntity.ok(response);
    }

    // Helper method to check if a number is prime
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
