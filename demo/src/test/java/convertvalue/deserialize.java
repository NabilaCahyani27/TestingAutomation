package convertvalue;

import org.testng.annotations.Test;

import demo.model.ResponseEmployee;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class deserialize {
      /*
     * Deserialize
     * json to object
     * {
     *     "first_name": "Andi","last_name": "Juntak","age": "20"
     * }
     * object = new RequestEmployee("Andi", "Juntak", "20");
     * object.getFirstName();
     */

     @Test
     public void testRegister(){
        RestAssured.baseURI = "https://whitesmokehouse.com";
        String bodyRegister = "{\n" + //
                    "  \"email\": \"albertsimanjuntak001111@gmail.com\",\n" + //
                    "  \"password\": \"@dmin123\",\n" + //
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
        ResponseEmployee responseEmployee = responseCreateEmployee.as(ResponseEmployee.class);
        System.out.println("Email: " + responseEmployee.email);
        System.out.println("Full Name: " + responseEmployee.fullName);

        System.out.println("Response: " + responseCreateEmployee.jsonPath().getString("email"));
        System.out.println("Response: " + responseCreateEmployee.jsonPath().getString("full_name"));

     }  
}
