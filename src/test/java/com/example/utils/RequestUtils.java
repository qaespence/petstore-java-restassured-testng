package com.example.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestUtils {

    private static final Faker faker = new Faker();

    // Generates a random payload for creating a pet
    public static Map<String, Object> generateRandomPet() {
        // Create the payload structure
        Map<String, Object> petPayload = new HashMap<>();

        // Randomize the id and other fields
        petPayload.put("id", faker.number().randomNumber());

        // Category object
        Map<String, Object> category = new HashMap<>();
        category.put("id", faker.number().randomNumber());
        category.put("name", faker.animal().name());
        petPayload.put("category", category);

        // Pet name
        petPayload.put("name", faker.name().firstName());

        // Photo URLs
        petPayload.put("photoUrls", List.of(faker.internet().url()));

        // Tags array
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", faker.number().randomNumber());
        tag.put("name", faker.lorem().word());
        petPayload.put("tags", List.of(tag));

        // Pet status
        petPayload.put("status", "available");

        return petPayload;
    }
}
