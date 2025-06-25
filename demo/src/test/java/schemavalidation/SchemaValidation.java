package schemavalidation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation {
    @Test
    public void testSchemaValidationRegister() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        String bodyRegister = "{\n" + //
                "  \"email\": \"albertjuntak987@gmail.com\",\n" + //
                "  \"password\": \"@admin123\",\n" + //
                "  \"full_name\":\"Albert Simanjuntak\",\n" + //
                "  \"department\":\"Technology\",\n" + //
                "  \"phone_number\":\"082264189134\"\n" + //
                "}";

        // Send POST request to employee endpoint
        Response responseCreateEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRegister)
                .log().all()
                .when()
                .post("/webhook/api/register");
        System.out.println("Response: " + responseCreateEmployee.asPrettyString());
        try {
            responseCreateEmployee.then().assertThat().body(matchesJsonSchemaInClasspath("register-schema.json"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void testSchemaValidationLogin() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        // Create login request
        String requestBody = "{\n" + //
              "  \"email\": \"albertsimanjuntak001111@gmail.com\",\n" + //
                "  \"password\": \"@admin123\"\n" + //
                "}";
        // Send POST request to login endpoint
        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/login");
        // Print the response
        System.out.println("Response: " + response.asPrettyString());

        try {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("login-schema.json"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
