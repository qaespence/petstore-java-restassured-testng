package com.example;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    private String suiteName;

    @BeforeSuite
    @Parameters("suiteName")  // Pass suiteName dynamically as a TestNG parameter
    public void setupSuite(String suiteName) throws FileNotFoundException {
        this.suiteName = suiteName != null ? suiteName : "DefaultSuite";  // Set default if no suiteName provided

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Create the logs directory if it doesn't exist
        Path logDir = Paths.get("logs");
        if (!Files.exists(logDir)) {
            try {
                Files.createDirectories(logDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Define the log file with the suite name, and clear the log file (overwrite mode)
        PrintStream logFile = new PrintStream(new FileOutputStream("logs/" + suiteName + "-log.txt", false));  // false to overwrite

        // Add a separator for the suite execution
        logFile.println("===== NEW TEST SUITE EXECUTION: " + suiteName + " =====");

        // Apply the filters for request and response logging
        RestAssured.filters(new RequestLoggingFilter(logFile), new ResponseLoggingFilter(logFile));
    }
}
