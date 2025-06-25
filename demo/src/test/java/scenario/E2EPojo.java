package scenario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.model.RequestLogin;
import demo.model.ResponseEmployee;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class E2EPojo {
    /*
     * List of test apis:
     * 1. register
     * 2. login
     * 3. GetListAllObjects
     * 4. AddObject
     * 5. ListOfObjectsById
     * 
     */

    /*
     * Scenario : RestAssured E2E Test
     * Test Case - 001: Register Employee
     * 1. Hit the endpoint register with valid data
     * 2. Hit the endpoint login with valid data
     * 
     * Test Case - 002: Add Barang
     * 1. Hit the endpoint login with valid data
     * 2. Hit the endpoint GetListAllObjects
     * 3. Hit the endpoint AddObject
     * 4. Hit the endpoint ListOfObjectsById to see data
     */
    String token, tokenLogin;

    @BeforeClass
    public void setup() {
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        // Login to the API and get the token
        String jsonLogin = "{\n" + //
                "  \"email\": \"albertsimanjuntak001111@gmail.com\",\n" + //
                "  \"password\": \"@admin123\"\n" + //
                "}";
        // Send POST request to login endpoint
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonLogin)
                .log().all()
                .when()
                .post("/webhook/api/login");
        Assert.assertEquals(responseLogin.getStatusCode(), 200,
                "Expected status code 200 but got " + responseLogin.getStatusCode());
        tokenLogin = responseLogin.jsonPath().getString("token");

    }

    @Test(priority = 1)
    public void registerEmployee() throws JsonProcessingException {
        /*
         * Test Case - 001: Register Employee
         * 1. Hit the endpoint register with valid data
         * 2. Hit the endpoint login with valid data
         */

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
        // Print the response
        System.out.println("Response: " + responseCreateEmployee.asPrettyString());

        ResponseEmployee resAdd = responseCreateEmployee.as(ResponseEmployee.class);

        Assert.assertEquals(responseCreateEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseCreateEmployee.getStatusCode());
        Assert.assertNotNull(resAdd.id, "Expected id but got null");
        Assert.assertEquals(resAdd.email, "albertjuntak987@gmail.com");
        Assert.assertEquals(resAdd.fullName, "Albert Simanjuntak");
        Assert.assertEquals(resAdd.department, "Technology");

        // Serialize the object to JSON
        RequestLogin requestLogin = new RequestLogin("albertjuntak987@gmail.com", "@admin123");
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonLogin = objectMapper.writeValueAsString(requestLogin);
        // Print the JSON string
        System.out.println("JSON String: " + jsonLogin);

        // Hit the endpoint login with valid data
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonLogin)
                .log().all()
                .when()
                .post("/webhook/employee/login");

        token = responseLogin.jsonPath().getString("token");
    }

    @Test(dependsOnMethods = "registerEmployee")
    public void addBaranng() {
        /*
         * Test Case - 002: Add Barang
         * 1. Hit the endpoint login with valid data
         * 2. Hit the endpoint GetListAllObjects
         * 3. Hit the endpoint AddObject
         * 4. Hit the endpoint ListOfObjectsById to see data
         */
          //Get Data Object 
        RestAssured.baseURI = "https://whitesmokehouse.com";
        // Send GET request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
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

        //Add Data Object
        String requestBody = "{\n"
                + "  \"name\": \"Apple MacBook Pro 17\",\n"
                + "  \"data\": {\n"
                + "    \"year\": 2019,\n"
                + "    \"price\": 1849.99,\n"
                + "    \"cpu_model\": \"Intel Core i9\",\n"
                + "    \"hard_disk_size\": \"1 TB\",\n"
                + "    \"capacity\": \"2 cpu\",\n"
                + "    \"screen_size\": \"14 Inch\",\n"
                + "    \"color\": \"red\"\n"
                + "  }\n"
                + "}";
        Response responseAddObject = RestAssured.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/objects");
        System.out.println("responseAddObject: " + responseAddObject.asPrettyString());

        // validate the response
         Assert.assertEquals(responseAddObject.getStatusCode(), 200,
                "Expected status code 200 but got " + responseAddObject.getStatusCode());
                Assert.assertNotNull(responseAddObject.jsonPath().get(
                "id"), "Expected id but got null");

        // Check data by ID
         Response responseGetObjectByID = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .log().all()
                .when()
                .get("/webhook/api/objectslistId?id=1090");
        
         System.out.println("responseAddObject: " + responseGetObjectByID.asPrettyString());
        
        // validate the response
         Assert.assertEquals(responseGetObjectByID.getStatusCode(), 200,
                "Expected status code 200 but got " + responseGetObjectByID.getStatusCode());
         Assert.assertNotNull(responseGetObjectByID.jsonPath().get(
                "id"), "Expected id but got null");
    }
}
