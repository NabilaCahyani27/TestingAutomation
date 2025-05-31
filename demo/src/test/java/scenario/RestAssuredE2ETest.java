package scenario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredE2ETest {
    /*
    List of test apis:
    1. register
    2. login
    3. GetListAllObjects
    4. AddObject
    5. ListOfObjectsById
    * 
    */


     /*
     * Scenario : RestAssured E2E Test
     * Test Case - 001: Register Employee
     * 1. Hit the endpoint register with valid data
     * 3. Hit the endpoint login with valid data
     * 
     * Test Case - 002: Add Barang
     * 1. Hit the endpoint login with valid data
     * 2. Hit the endpoint GetListAllObjects
     * 3. Hit the endpoint AddObject
     * 4. Hit the endpoint ListOfObjectsById to see data
     */

     String token, tokenLogin;

     //Login
    @BeforeClass
    public void Login() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
        String requestBody = "{\n"
                + "  \"email\": \"albertjuntak12345@gmail.com\",\n"
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

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but got " + response.getStatusCode());
        tokenLogin = response.jsonPath().getString("token");
    }

    @Test(priority = 1)
    public void registerEmployee(){
          /*
     * Scenario : RestAssured E2E Test
     * Test Case - 001: Register Employee
     * 1. Hit the endpoint register with valid data
     * 3. Hit the endpoint login with valid data
     */

        RestAssured.baseURI = "https://whitesmokehouse.com";
        String requestBody = "{\n"
                + //
                "    \"email\": \"albertjuntak12345@gmail.com\",\n"
                + //
                "    \"full_name\": \"Albert Simanjuntak\",\n"
                + //
                "    \"department\": \"Technology\",\n"
                + //
                "    \"title\": \"Technology\",\n"
                + //
                "    \"password\" : \"@dmin123\",\n"
                + //
                "    \"phone_number\" : \"082264189134\"\n"
                + //
                "}";
        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/register");
        // Print Response
        System.out.println("Response: " + response.asPrettyString());

        // validate the response
         Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but got " + response.getStatusCode());
        
        Assert.assertNotNull(response.jsonPath().get(
                "id"), "Expected id but got null");
        Assert.assertEquals(response.jsonPath().get("email"),"albertjuntak12345@gmail.com",
                "Expected email albertjuntak12345@gmail.com"
                        + " but got " + response.jsonPath().get("email"));
    }

    @Test(priority = 2)
    public void addDataObject(){
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
