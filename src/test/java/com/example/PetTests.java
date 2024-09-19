package com.example;

import com.example.utils.RequestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PetTests extends BaseTest {

    @Test
    public void testCreateRandomPet() {
        // Generate a random pet payload
        Map<String, Object> randomPet = RequestUtils.generateRandomPet();

        // Create a new pet with randomized data
        Response response = given()
                .header("Content-Type", "application/json")
                .body(randomPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract().response();

        // Log and verify the response
        assertEquals(response.jsonPath().getString("name"), randomPet.get("name"));
    }
}
