package com.brutallyhonestmirror.e2e.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    private static final String BASE_URL = "http://localhost:8080";

    private String email;
    private String password;
    private HttpResponse<String> response;

    @Given("a unique email and a valid password")
    public void aUniqueEmailAndAValidPassword() {
        email = "testuser" + System.currentTimeMillis() + "@example.com";
        password = "testpassword123";
    }

    @When("the user registers via the API")
    public void theUserRegistersViaTheApi() throws Exception {
        String requestBody = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}", email, password
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    @Then("the response should contain a valid token")
    public void theResponseShouldContainAValidToken() throws Exception {
        assertTrue("Expected 200 OK but got " + response.statusCode(),
                response.statusCode() == 200);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.body());
        String token = json.get("token").asText();

        assertNotNull(token);
        assertTrue("Token should not be empty", !token.isEmpty());
    }

    }
