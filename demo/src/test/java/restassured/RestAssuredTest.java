package restassured;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredTest {

    String token;

    // Register
    // @Test
    // public void Register() {
    //     RestAssured.baseURI = "https://whitesmokehouse.com";
    //     String requestBody = "{\n"
    //             + //
    //             "    \"email\": \"albertjuntak06@gmail.com\",\n"
    //             + //
    //             "    \"full_name\": \"Albert Simanjuntak\",\n"
    //             + //
    //             "    \"department\": \"science\",\n"
    //             + //
    //             "    \"title\": \"Technology\",\n"
    //             + //
    //             "    \"password\" : \"@dmin123\",\n"
    //             + //
    //             "    \"phone_number\" : \"12345678\"\n"
    //             + //
    //             "}";
    //     Response response = RestAssured.given()
    //             .contentType("application/json")
    //             .header("Content-Type", "application/json")
    //             .body(requestBody)
    //             .log().all()
    //             .when()
    //             .post("/webhook/api/register");
    //     System.out.println("Response: " + response.asPrettyString());
    // }
    // Login
    @Test
    public void Login() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        String requestBody = "{\n"
                + "  \"email\": \"albertsimanjuntak227@gmail.com\",\n"
                + "  \"password\": \"@dmin123\"\n"
                + "}";
        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/login");
        token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
    }

    // Get All Object
    @Test(dependsOnMethods = "Login")
    public void GetObjectt() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        // Send GET request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/objects");
        // Print the response
        List<Map<String, Object>> allObjects = response.jsonPath().getList("$");
        List<Map<String, Object>> firstFiveObjects = allObjects.stream()
                .limit(5)
                .collect(Collectors.toList());

        for (Map<String, Object> obj : firstFiveObjects) {
            System.out.println(obj);
        }

        // System.out.println("Response: " + response.asPrettyString());
    }

    // Add Object
    // @Test(dependsOnMethods = "Login", priority = 2)
    // public void AddObject() {
    //     RestAssured.baseURI = "https://whitesmokehouse.com";
    //     String requestBody = "{\n"
    //             + "  \"name\": \"Apple MacBook Pro 17\",\n"
    //             + "  \"data\": {\n"
    //             + "    \"year\": 2019,\n"
    //             + "    \"price\": 1849.99,\n"
    //             + "    \"cpu_model\": \"Intel Core i9\",\n"
    //             + "    \"hard_disk_size\": \"1 TB\",\n"
    //             + "    \"capacity\": \"2 cpu\",\n"
    //             + "    \"screen_size\": \"14 Inch\",\n"
    //             + "    \"color\": \"red\"\n"
    //             + "  }\n"
    //             + "}";
    //     Response response = RestAssured.given()
    //             .contentType("application/json")
    //             .header("Content-Type", "application/json")
    //             .header("Authorization", "Bearer " + token)
    //             .body(requestBody)
    //             .log().all()
    //             .when()
    //             .post("/webhook/api/objects");
    //     System.out.println("Response: " + response.asPrettyString());
    // }
    // Update (put)
    // @Test(dependsOnMethods = "Login", priority = 3)
    // public void UpdateObject() {
    //     RestAssured.baseURI = "https://whitesmokehouse.com";
    //     String requestBody = "{\n"
    //             + "  \"name\": \"Apple MacBook Pro 17 Update\",\n"
    //             + "  \"data\": {\n"
    //             + "    \"year\": 2019,\n"
    //             + "    \"price\": 1849.99,\n"
    //             + "    \"cpu_model\": \"Intel Core i9\",\n"
    //             + "    \"hard_disk_size\": \"1 TB\",\n"
    //             + "    \"capacity\": \"2 cpu\",\n"
    //             + "    \"screen_size\": \"14 Inch\",\n"
    //             + "    \"color\": \"red\"\n"
    //             + "  }\n"
    //             + "}";
    //     Response response = RestAssured.given()
    //             .contentType("application/json")
    //             .header("Content-Type", "application/json")
    //             .header("Authorization", "Bearer " + token)
    //             .body(requestBody)
    //             .log().all()
    //             .when()
    //             .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/544");
    //     System.out.println("Response: " + response.asPrettyString());
    // }
    //update (patch)
    @Test(dependsOnMethods = "Login", priority = 5)
    public void UpdatePartiallyObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        String requestBody = "{\n"
                + "  \"name\": \"Apple MacBook Pro 1611-albert12\",\n"
                + "    \"year\": 2030\n"
                + "}";
        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .log().all()
                .when()
                .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/544");
        System.out.println("Response: " + response.asPrettyString());
    }

    // Delete Object
    @Test(dependsOnMethods = "Login", priority = 4)
    public void DeleteObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/548");
        // Print the response
        System.out.println("Response: " + response.asPrettyString());
    }
}
